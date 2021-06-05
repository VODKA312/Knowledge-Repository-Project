<template>
  <a-layout>
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
                  <user-outlined />
                  subnav 1111
                </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
                <span>
                  <laptop-outlined />
                  subnav 2
                </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
                <span>
                  <notification-outlined />
                  subnav 3
                </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout style="padding: 0 24px 24px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout-content
              :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <pre>{{ebooks}}</pre>
      </a-layout-content>
    </a-layout>
    </a-layout>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import axios from 'axios';

export default defineComponent({
  name: 'Home',
  setup(){
    console.log("set up");
    const ebooks = ref(); //定义一个变量接收数据并渲染在前端上,使用ref()让它变成响应式的数据，vue3新特性
    onMounted(function () {
      /*
      1,初始化
       */
      //请求地址
      console.log("onMounted")
      axios.get("http://localhost:8088/ebook/list?name=算法")
              .then(function(response){ //也可以写成(response) =>
                //拿到响应的数据
                const data = response.data;
                ebooks.value = data.content;
                console.log(response);//打印响应结果
              });
    })
    return{
      ebooks,
    }
  },
  components: {
  },
});
</script>
