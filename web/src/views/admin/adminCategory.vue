<template>
    <a-layout>
        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
            <p>
                <a-form layout="inline" :model="param">
                    <a-form-item>
                        <a-input v-model:value="param.name" placeholder="名称">
                        </a-input>
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" @click="handleQuery()">
                            查询
                        </a-button>
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" @click="add()">
                            新增
                        </a-button>
                    </a-form-item>
                </a-form>
            </p>
            <a-table
                    v-if="level1.length > 0"
                    :columns="columns"
                    :data-source="level1"
                    :row-key="record => record.id"
                    :loading="loading"
                    :pagination="false"
            >
                <template #cover="{ text: cover }">
                    <img v-if="cover" :src="cover" alt="avatar" />
                </template>
                <template v-slot:action="{ text, record }">
                    <a-space size="small">
                        <a-button type="primary" @click="edit(record)">
                            编辑
                        </a-button>
                        <!-- 删除开始 -->
                        <!-- 点击事件@confirm -->
                        <a-popconfirm
                                title="你确认要删掉这条记录吗？"
                                ok-text="是"
                                cancel-text="否"
                                @confirm="del(record.id)"
                                @cancel="cancel"
                        >
                            <a-button type="danger" >
                                删除
                            </a-button>
                        </a-popconfirm>
                    </a-space>
                </template>
            </a-table>
        </a-layout-content>
    </a-layout>
    <a-modal
            title="分类表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <a-form :model="catgory" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="序号">
                <a-input v-model:value="category.sort" />
            </a-form-item>
            <a-form-item label="名称">
                <a-input v-model:value="category.name" />
            </a-form-item>
            <!-- 没有做下拉框的选项
            <a-form-item label="父分类编号">
                <a-input v-model:value="category.parent" />
            </a-form-item>
            <a-form-item label="子分类编号">
                <a-input v-model:value="category.id" />
            </a-form-item> -->
            <a-form-item label="父分类">
                <a-select
                        v-model:value="category.parent"
                        ref="select"
                >
                    <a-select-option :value="0">
                        无
                    </a-select-option>
                    <a-select-option v-for="c in level1"
                                     :key="c.id"
                                     :value="c.id"
                                     :disabled="category.id === c.id">
                        {{c.name}}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="顺序">
                <a-input v-model:value="category.sort" />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script lang="ts">
    import { defineComponent, onMounted, ref } from 'vue';
    import axios from 'axios';
    import { message } from 'ant-design-vue'; //引入消息提示组件
    import {Tool} from "@/util/tool";

    const columns = [

        {
          title: '编号',
          dataIndex: 'id'
        },
        {
            title: '名称',
            dataIndex: 'name'
        },
        // {
        //     title: '父分类编号',
        //     key: 'parent',
        //     dataIndex: 'parent'
        // },
        {
            title: '顺序',
            dataIndex: 'sort'
        },
        {
            title: '操作',
            key: 'action',
            slots: { customRender: 'action' }
        }
    ];

    /**
     * 一级分类树，children属性就是二级分类
     * [{
     *   id: "",
     *   name: "",
     *   children: [{
     *     id: "",
     *     name: "",
     *   }]
     * }]
     */
    const level1 = ref(); // 一级分类树，children属性就是二级分类
    level1.value = [];

    export default defineComponent({
        name: 'adminCategory',
        setup() {
            const param = ref();
            param.value = {};
            const categorys = ref();
            const loading = ref(false);
            /**
             * 数据查询
             **/
            const handleQuery = () => {
                loading.value = true;
                axios.get("/category/all").then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if(data.success){
                        categorys.value = data.content;
                        console.log("原始数组：", categorys.value);
                        level1.value = [];
                        level1.value = Tool.array2Tree(categorys.value, 0);
                        console.log("树形结构：", level1);
                        //categorys.value = level1.value
                    }
                    else{
                        message.error(data.message); //打印错误信息
                    }
                });
            };

            // -------- 表单 ---------
            const category = ref(); //定义一个响应式变量
            const modalVisible = ref(false);
            const modalLoading = ref(false);
            /**
             * 当点击ok按钮时，触发的事件
             **/
            const handleModalOk = () => {
                modalLoading.value = true;
                // setTimeout(() => {
                // }, 2000);
                axios.post("/category/save", category.value).then((response) => {
                    modalLoading.value = false; //前端返回参数了，就把loading效果去掉
                    const data = response.data; //接受响应的数据 data = CommonResp
                    if (data.success) {
                        //modalLoading.value = false;
                        modalVisible.value = false;
                        // 重新加载数据
                        handleQuery();
                    }
                    else {
                        message.error(data.message);
                    }
                });
            };

            /**
             * 编辑
             */
            const edit = (record :any) => {
                modalVisible.value = true;
                category.value = Tool.copy(record); //把值复制过来
            };

            /**
             * 新增
             */
            const add = () => {
                modalVisible.value = true;
                category.value = {}; //清空
            };

            /**
             * 删除
             */
            const del = (id : number) => { //long -> number
                //发送一个delete请求
                axios.delete("/category/delete/"+id).then((response) => {
                    const data = response.data; //接受响应的数据 data = CommonResp
                    //如果判断成功
                    if (data.success) {
                        // 重新加载数据
                        handleQuery();
                    }
                    else {
                        //message.error(data.message);
                    }
                });
            };

            onMounted(() => {
                handleQuery(); //初始的时候查一次
            });

            return {
                param,
                //categorys,
                level1,
                columns,
                loading,
                handleQuery,

                edit,
                add,
                del,

                modalVisible,
                modalLoading,
                handleModalOk,
                category
            };
        },
    });
</script>

<style scoped>
    img {
        width: 50px;
        height: 50px;
    }
    .ant-btn-primary {
        color: #fff;
        background-color: #42b983;
        border-color: #42b983;
    }
    .ant-btn-primary:hover{
        background-color: #42b983;
    }
</style>