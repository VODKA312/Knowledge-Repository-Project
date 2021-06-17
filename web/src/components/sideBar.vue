<template>
    <a-layout-sider
            breakpoint="lg"
            collapsed-width="0"
            @collapse="onCollapse"
            @breakpoint="onBreakpoint"
    >
        <a-menu
                mode="inline"
                v-model:selectedKeys="selectedKeys2"
                v-model:openKeys="openKeys"
                :style="{ height: '100%', borderRight: 0 }"
                @click="handleClick"
        >
            <a-menu-item key="welcome">
                <MailOutlined />
                <span>欢迎</span>
            </a-menu-item>
            <a-sub-menu key="sub1">
                <template #title>
                <span>
                  <laptop-outlined />
                  资源管理界面
                </span>
                </template>
                <a-menu-item key="/admin/ebook">
                    <router-link to="/admin/ebook">电子书管理</router-link>
                </a-menu-item>
                <a-menu-item key="/admin/category">
                    <router-link to="/admin/category">分类管理</router-link>
                </a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub2">
                <template #title>
                <span>
                  <user-outlined />
                  用户管理界面
                </span>
                </template>
                <a-menu-item key="5">option5</a-menu-item>
            </a-sub-menu>
            <a-sub-menu key="sub3">
                <template #title>
                        <span>
                          <notification-outlined />
                          电子书文档
                        </span>
                </template>
                <!--
                <a-sub-menu key="sub4" title="一级菜单">
                    <a-menu-item key="6">二级菜单1</a-menu-item>
                    <a-menu-item key="7">二级菜单2</a-menu-item>
                </a-sub-menu> -->
                <router-link to="/ebook">
                    <a-menu-item key="6">电子书总文档库</a-menu-item>
                </router-link>
                <a-sub-menu v-for="item in level1" :key="item.id">
                    <template v-slot:title>
                        <span><user-outlined />{{item.name}}</span>
                    </template>
                    <a-menu-item v-for="child in item.children" :key="child.id">
                        <MailOutlined /><span>{{child.name}}</span>
                    </a-menu-item>
                </a-sub-menu>
            </a-sub-menu>
        </a-menu>
    </a-layout-sider>
</template>

<script lang="ts">
    import { defineComponent,onMounted, ref } from 'vue';
    import axios from 'axios';
    import { message } from 'ant-design-vue';
    import {Tool} from "@/util/tool";

    export default defineComponent({
        name: 'sideBar',
        setup(){
            const ebooks = ref();
            const openKeys =  ref();
            const level1 =  ref();
            let categorys: any;
            /**
             * 查询所有分类
             **/
            const handleQueryCategory = () => {
                axios.get("/category/all").then((response) => {
                    const data = response.data;
                    if (data.success) {
                        categorys = data.content;
                        console.log("原始数组：", categorys);
                        // 加载完分类后，将侧边栏全部展开
                        openKeys.value = [];
                        for (let i = 0; i < categorys.length; i++) {
                            openKeys.value.push(categorys[i].id)
                        }
                        level1.value = [];
                        level1.value = Tool.array2Tree(categorys, 0);
                        console.log("树形结构：", level1.value);
                    } else {
                        message.error(data.message);
                    }
                });
            }

            const isShowWelcome = ref(true);
            let categoryId2 = 0;

            const handleQueryEbook = () => {
                axios.get("/ebook/list", {
                    params: {
                        page: 1,
                        size: 10,
                        categoryId2: categoryId2
                    }
                }).then((response) => {
                    const data = response.data;
                    ebooks.value = data.content.list;
                    // ebooks1.books = data.content;
                });
            };

            const handleClick = (value: any) => {
                // console.log("menu click", value)
                if (value.key === 'welcome') {
                    isShowWelcome.value = true;
                } else {
                    categoryId2 = value.key;
                    isShowWelcome.value = false;
                    handleQueryEbook();
                }
                // isShowWelcome.value = value.key === 'welcome';
            };
            onMounted(() => {
                handleQueryCategory();
                //handleQueryEbook();
            });

            return {
                ebooks,
                // ebooks2: toRef(ebooks1, "books"),
                level1,
                openKeys,
                handleClick,
                isShowWelcome,
            }
        }
    });
</script>

<style scoped>

</style>