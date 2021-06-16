<template>
    <a-layout>
        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
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
                        <a-button type="primary" @click="edit">
                            编辑
                        </a-button>
                        <a-button type="danger">
                            删除
                        </a-button>
                    </a-space>
                </template>
            </a-table>
        </a-layout-content>
    </a-layout>
    <a-modal
            title="修改电子书表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <p>Test</p>
    </a-modal>
</template>

<script lang="ts">
    import { defineComponent, onMounted, ref } from 'vue';
    import axios from 'axios';

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

    // const data = [
    //     {
    //         id:1,
    //         category1Id: null,
    //         category2Id: null,
    //         cover: "/image/coverTest.jpg",
    //         description: "测试文档1",
    //         docCount: null,
    //         name: "操作系统算法测试1",
    //         viewCount: null,
    //         voteCount: null,
    //     },
    //     {
    //         id:2,
    //         category1Id: null,
    //         category2Id: null,
    //         cover: "/image/coverTest.jpg",
    //         description: "测试文档2",
    //         docCount: null,
    //         name: "操作系统算法测试2",
    //         viewCount: null,
    //         voteCount: null
    //     },
    //     {
    //         id: 3,
    //         category1Id: null,
    //         category2Id: null,
    //         cover: "/image/coverTest.jpg",
    //         description: "测试文档3",
    //         docCount: null,
    //         name: "操作系统算法测试3",
    //         viewCount: null,
    //         voteCount: null
    //     },
    // ];

    export default defineComponent({
        name: 'adminEbook',
        setup() {
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
                        size:params.size
                    }
                }).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    ebooks.value = data.content.list;
                    // 重置分页按钮
                    pagination.value.current = params.page;
                    pagination.value.total = data.content.total;
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
            const modalVisible = ref(false);
            const modalLoading = ref(false);
            const handleModalOk = () => {
                modalLoading.value = true;
                setTimeout(() => {
                    modalLoading.value = false;
                    modalVisible.value = false;
                }, 2000);
            };

            /**
             * 编辑
             */
            const edit = () => {
                modalVisible.value = true;
            };

            onMounted(() => {
                handleQuery({
                    page:1,
                    size:pagination.value.pageSize //传入前端设置好的分页值
                }); //初始的时候查一次
            });

            return {
                ebooks,
                pagination,
                columns,
                loading,
                handleTableChange,
                edit,
                modalVisible,
                modalLoading,
                handleModalOk
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
        text-shadow: 0 -1px 0 rgb(0 0 0 / 12%);
        box-shadow: 0 2px 0 rgb(0 0 0 / 5%);
    }
    .ant-btn-primary:hover{
        background-color: #42b983;
    }
</style>