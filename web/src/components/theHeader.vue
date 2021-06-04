<template>
    <a-layout-header class="header" :style="{background:'#1d292e'}">
        <div class="logo" />
        <a-menu
                theme="dark"
                mode="horizontal"
                v-model:selectedKeys="selectedKeys1"
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
    <!--
    <div class="site-navigation main_menu menu-style-2" id="mainmenu-area">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid px-4">
                <a class="navbar-brand" href="index.html">
                    <img src="../assets/images/logo.png" alt="Eduhash" class="img-fluid">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarMenu">
                    <form action="#" class="header-form ml-5 d-none d-xl-block">
                        <input type="text" class="form-control" placeholder="search">
                        <i class="fa fa-search"></i>
                    </form>

                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Home<i class="fa fa-angle-down"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbar3">
                                <a class="dropdown-item " href="index.html">
                                    Home 1
                                </a>
                                <a class="dropdown-item " href="index-2.html">
                                    Home 2
                                </a>
                                <a class="dropdown-item " href="index-3.html">
                                    Home 3
                                </a>
                                <a class="dropdown-item " href="index-4.html">
                                    Home 4
                                </a>
                            </div>
                        </li>
                        <li class="nav-item ">
                            <a href="about.html" class="nav-link js-scroll-trigger">
                                About us
                            </a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Courses<i class="fa fa-angle-down"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbar3">
                                <a class="dropdown-item " href="course-grid.html">
                                    Course Style 1
                                </a>
                                <a class="dropdown-item " href="course-grid-2.html">
                                    Course Style 2
                                </a>

                                <a class="dropdown-item " href="course-grid-3.html">
                                    Course Style 3
                                </a>
                                <a class="dropdown-item " href="course-grid-4.html">
                                    Course Style 4
                                </a>
                                <a class="dropdown-item " href="course-single.html">
                                    Course Details Style 1
                                </a>
                                <a class="dropdown-item " href="course-single2.html">
                                    Course Details Style 2
                                </a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Pages<i class="fa fa-angle-down"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbar3">
                                <a class="dropdown-item " href="login.html">
                                    Login
                                </a>
                                <a class="dropdown-item " href="register.html">
                                    Register
                                </a>
                                <a class="dropdown-item " href="cart.html">
                                    cart
                                </a>
                                <a class="dropdown-item " href="checkout.html">
                                    checkout
                                </a>
                                <a class="dropdown-item " href="error.html">
                                    404
                                </a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbar3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Blog<i class="fa fa-angle-down"></i>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbar3">
                                <a class="dropdown-item " href="blog.html">
                                    Blog
                                </a>
                                <a class="dropdown-item " href="blog-single.html">
                                    Blog Single
                                </a>
                            </div>
                        </li>

                        <li class="nav-item ">
                            <a href="contact.html" class="nav-link">
                                Contact
                            </a>
                        </li>
                    </ul>

                    <div class="d-flex align-items-center">
                        <div class="header-login ml-3">
                            <a href="#" class="btn btn-solid-border btn-sm ">Log In</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div> -->
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
    .ant-layout-header {
        height: 64px;
        padding: 0 50px;
        line-height: 64px;
        background: #1d292e;
    }
    .nav a{
        color: #adb4b7;
    }
    [data-theme='dark'] .ant-menu.ant-menu-dark .ant-menu-item-selected, .ant-menu-submenu-popup.ant-menu-dark .ant-menu-item-selected {
        background-color: #42b983;
    }
</style>