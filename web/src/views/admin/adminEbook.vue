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
                        <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
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
            <a-table :columns="columns"
                     :data-source="ebooks"
                     :row-key="record => record.id"
                     :loading="loading"
                     @change="handleTableChange"
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
            title="电子书表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="封面">
                <a-input v-model:value="ebook.cover" />
            </a-form-item>
            <a-form-item label="名称">
                <a-input v-model:value="ebook.name" />
            </a-form-item>
            <a-form-item label="一级分类">
                <a-input v-model:value="ebook.category1Id" />
            </a-form-item>
            <a-form-item label="二级分类">
                <a-input v-model:value="ebook.category2Id" />
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="ebook.description" type="textarea" />
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
            title: '封面',
            dataIndex: 'cover',
            slots: { customRender: 'cover' }
        },
        {
            title: '名称',
            dataIndex: 'name'
        },
        {
            title: '一级分类',
            key: 'category1Id',
            dataIndex: 'category1Id'
        },
        {
            title: '二级分类',
            dataIndex: 'category2Id'
        },
        {
            title: '文档数',
            dataIndex: 'docCount'
        },
        {
            title: '阅读数',
            dataIndex: 'viewCount'
        },
        {
            title: '点赞数',
            dataIndex: 'voteCount'
        },
        {
            title: '操作',
            key: 'action',
            slots: { customRender: 'action' }
        }
    ];

    export default defineComponent({
        name: 'adminEbook',
        setup() {
            const param = ref();
            param.value = {};
            const ebooks = ref();
            const pagination = ref({
                current: 1,
                pageSize: 5,//分页数：每页五个
                total: 0
            });
            const loading = ref(false);
            /**
             * 数据查询
             **/
            const handleQuery = (params: any) => {
                loading.value = true;
                axios.get("/ebook/list", {
                    params:{//将需要用的参数传进来
                        page:params.page,
                        size:params.size,
                        name:param.value.name //param传进去的name参数
                    }
                }).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if(data.success){
                        ebooks.value = data.content.list;
                        // 重置分页按钮
                        pagination.value.current = params.page;
                        pagination.value.total = data.content.total;
                    }
                    else{
                        message.error(data.message); //打印错误信息
                    }
                });
            };

            /**
             * 表格点击页码时触发
             */
            const handleTableChange = (pagination: any) => {
                console.log("看看自带的分页参数都有啥：" + pagination);
                handleQuery({
                    page: pagination.current,
                    size: pagination.pageSize
                });
            };

            // -------- 表单 ---------
            const ebook = ref(); //定义一个响应式变量
            const modalVisible = ref(false);
            const modalLoading = ref(false);
            /**
             * 当点击ok按钮时，触发的事件
             **/
            const handleModalOk = () => {
                modalLoading.value = true;
                // setTimeout(() => {
                // }, 2000);
                axios.post("/ebook/save", ebook.value).then((response) => {
                    modalLoading.value = false; //前端返回参数了，就把loading效果去掉
                    const data = response.data; //接受响应的数据 data = CommonResp
                    if (data.success) {
                        //modalLoading.value = false;
                        modalVisible.value = false;
                        // 重新加载数据
                        handleQuery({
                            page: pagination.value.current,
                            size: pagination.value.pageSize,
                        });
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
                ebook.value = Tool.copy(record); //把值复制过来
            };

            /**
             * 新增
             */
            const add = () => {
                modalVisible.value = true;
                ebook.value = {}; //清空
            };

            /**
             * 删除
             */
            const del = (id : number) => { //long -> number
                //发送一个delete请求
                axios.delete("/ebook/delete/"+id).then((response) => {
                    const data = response.data; //接受响应的数据 data = CommonResp
                    //如果判断成功
                    if (data.success) {
                        // 重新加载数据
                        handleQuery({
                            page: pagination.value.current,
                            size: pagination.value.pageSize,
                        });
                    }
                    else {
                        //message.error(data.message);
                    }
                });
            };

            onMounted(() => {
                handleQuery({
                    page:1,
                    size:pagination.value.pageSize //传入前端设置好的分页值
                }); //初始的时候查一次
            });

            return {
                param,
                ebooks,
                pagination,
                columns,
                loading,
                handleTableChange,
                handleQuery,

                edit,
                add,
                del,

                modalVisible,
                modalLoading,
                handleModalOk,
                ebook
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