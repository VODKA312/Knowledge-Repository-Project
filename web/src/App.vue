<template>
  <a-layout>
    <the-header></the-header>
    <a-layout>
      <side-bar></side-bar>
      <a-layout style="padding: 0 24px 24px">
        <!-- 引入面包屑导航组件 -->
        <bread-crumb></bread-crumb>
        <router-view />
      </a-layout>
    </a-layout>
    <!--<side-bar></side-bar> -->
    <the-footer></the-footer>
  </a-layout>
  <!--
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>
  <router-view/> -->
</template>

<script lang="ts">
  import { defineComponent } from 'vue';
  import theHeader from "@/components/theHeader.vue";
  import theFooter from "@/components/theFooter.vue";
  import sideBar from "@/components/sideBar.vue"; //引入侧边栏组件
  import breadCrumb from "@/components/breadCrumb.vue"; //引入面包屑导航组件
  export default defineComponent({
    name: 'app',
    components: {
      theHeader,
      theFooter,
      sideBar, //引入侧边栏组件
      breadCrumb,
    }
  });
</script>
<style>
  /**
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}*/
  #components-layout-demo-top-side-2 .logo {
    float: left;
    width: 120px;
    height: 31px;
    margin: 16px 24px 16px 0;
    background: rgba(255, 255, 255, 0.3);
  }

  .ant-row-rtl #components-layout-demo-top-side-2 .logo {
    float: right;
    margin: 16px 0 16px 24px;
  }

  .site-layout-background {
    background: #fff;
  }
</style>
