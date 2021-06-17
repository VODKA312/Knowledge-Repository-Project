<template>
    <div>
        <a-breadcrumb style="margin: 16px 0" :routes="routes">
            <!--
            <a-breadcrumb-item>Home</a-breadcrumb-item>
            <a-breadcrumb-item>List</a-breadcrumb-item>
            <a-breadcrumb-item>App</a-breadcrumb-item> -->
            <template #itemRender="{ route, routes, paths }">
                <span v-if="routes.indexOf(route) === routes.length - 1">
                  {{ route.breadcrumbName }}
                </span>
                <router-link v-else :to="`${basePath}/${paths.join('/')}`">
                    {{ route.breadcrumbName }}
                </router-link>
            </template>
            <a-breadcrum-item><p>{{ $route.path }}</p></a-breadcrum-item>
        </a-breadcrumb>
    </div>
</template>

<script lang="ts">
    import { defineComponent, ref } from 'vue';

    interface Route {
        path: string;
        breadcrumbName: string;
        children?: Array<{
            path: string;
            breadcrumbName: string;
        }>;
    }
    export default defineComponent({
        name: 'breadCrumb', //定义该组件名称
        setup() {
            const routes = ref<Route[]>([
                {
                    path: '',
                    breadcrumbName: 'home',
                },
                //这里定义一个展开的路径
                {
                    path: '',
                    breadcrumbName: 'category',
                    children: [
                        {
                            path: '/admin',
                            breadcrumbName: 'manage',
                        },
                        {
                            path:'/ebook',
                            breadcrumbName: 'resource'
                        }
                    ],
                }
            ]);
            return {
                basePath: '',
                routes,
            };
        },
    });
</script>

<style scoped>

</style>