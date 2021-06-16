<template>
    <a-layout-header class="header">
        <div class="logo" />
        <a-menu
                theme="dark"
                mode="horizontal"
                :style="{ lineHeight: '64px',background:'#1d292e' }"
        >
            <a-menu-item class="navbar-brand">
                <img src="../assets/images/logo-dark.png" alt="EduOnline" class="img-fluid">
            </a-menu-item>
            <a-menu-item key="1" :class="nav">首页</a-menu-item>
            <a-menu-item key="2">关于我们</a-menu-item>
            <a-menu-item key="3">在线课程</a-menu-item>
            <a-menu-item key="4">教学博客</a-menu-item>
            <a-menu-item key="5">资源下载</a-menu-item>
            <a-menu-item key="6">在线编程</a-menu-item>
            <a-menu-item key="7">在线知识库</a-menu-item>
        </a-menu>
    </a-layout-header>
</template>

<script lang="ts">
    import { defineComponent } from 'vue';

    export default defineComponent({
        name: 'header',
    });
</script>

<style>
    .navbar-brand img {
        width: 125px;
    }
    .img-fluid {
        max-width: 100%;
        height: auto;
    }
    img {
        vertical-align: middle;
        border-style: none;
    }
    .ant-layout-header.header{
        height: 64px;
        padding: 0 50px;
        line-height: 64px;
        background: #1d292e;
    }
    .ant-menu-dark.ant-menu-horizontal {
        border-bottom: 0;
    }
    .nav a{
        color: #adb4b7;
    }
    .ant-menu.ant-menu-dark .ant-menu-item-selected, .ant-menu-submenu-popup.ant-menu-dark .ant-menu-item-selected {
        background-color: #42b983;
    }
</style>