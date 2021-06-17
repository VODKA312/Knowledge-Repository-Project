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
        >
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
                  subnav 3
                </span>
                </template>
                <a-menu-item key="9">option9</a-menu-item>
            </a-sub-menu>
        </a-menu>
    </a-layout-sider>
</template>

<script>
    import { defineComponent } from 'vue';

    export default defineComponent({
        name: 'sideBar',
    });
</script>

<style scoped>

</style>