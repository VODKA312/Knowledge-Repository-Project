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
  import { defineComponent,onMounted, ref } from 'vue';
  import theHeader from "@/components/theHeader.vue";
  import theFooter from "@/components/theFooter.vue";
  import sideBar from "@/components/sideBar.vue"; //引入侧边栏组件
  import breadCrumb from "@/components/breadCrumb.vue"; //引入面包屑导航组件
  import axios from 'axios';
  import { message } from 'ant-design-vue';
  import {Tool} from "@/util/tool";
  export default defineComponent({
    name: 'app',
    components: {
      theHeader,
      theFooter,
      sideBar, //引入侧边栏组件
      breadCrumb,
    },
    // setup(){
    //   const ebooks = ref();
    //   const openKeys =  ref();
    //   const level1 =  ref();
    //   let categorys: any;
    //   /**
    //    * 查询所有分类
    //    **/
    //   const handleQueryCategory = () => {
    //     axios.get("/category/all").then((response) => {
    //       const data = response.data;
    //       if (data.success) {
    //         categorys = data.content;
    //         console.log("原始数组：", categorys);
    //
    //         // 加载完分类后，将侧边栏全部展开
    //         openKeys.value = [];
    //         for (let i = 0; i < categorys.length; i++) {
    //           openKeys.value.push(categorys[i].id)
    //         }
    //
    //         level1.value = [];
    //         level1.value = Tool.array2Tree(categorys, 0);
    //         console.log("树形结构：", level1.value);
    //       } else {
    //         message.error(data.message);
    //       }
    //     });
    //   }
    //
    //   const isShowWelcome = ref(true);
    //   let categoryId2 = 0;
    //
    //   const handleQueryEbook = () => {
    //     axios.get("/ebook/list", {
    //       params: {
    //         page: 1,
    //         size: 1000,
    //         categoryId2: categoryId2
    //       }
    //     }).then((response) => {
    //       const data = response.data;
    //       ebooks.value = data.content.list;
    //       // ebooks1.books = data.content;
    //     });
    //   };
    //
    //   const handleClick = (value: any) => {
    //     // console.log("menu click", value)
    //     if (value.key === 'welcome') {
    //       isShowWelcome.value = true;
    //     } else {
    //       categoryId2 = value.key;
    //       isShowWelcome.value = false;
    //       handleQueryEbook();
    //     }
    //     // isShowWelcome.value = value.key === 'welcome';
    //   };
    //   onMounted(() => {
    //     handleQueryCategory();
    //     //handleQueryEbook();
    //   });
    //
    //   return {
    //     ebooks,
    //     // ebooks2: toRef(ebooks1, "books"),
    //     level1,
    //     openKeys,
    //     handleClick,
    //     isShowWelcome,
    //   }
    // }
  });
</script>
<style>
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
