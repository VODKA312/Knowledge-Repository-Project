<template>
  <!-- 主体部分 -->
  <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
  >
    <div class="welcome" v-show="isShowWelcome">
        Welcome
    </div>
    <!-- 电子书展示部分 -->
    <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :pagination="pagination" :data-source=ebooks>
      <template #renderItem="{ item }">
        <a-list-item key="item.name">
          <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component v-bind:is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.href">{{ item.name }}</a>
            </template>
            <template #avatar><a-avatar :src="item.cover" /></template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </a-layout-content>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref } from 'vue';
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
      console.log("onMounted112")
      axios.get("/ebook/list",{
        params:{
          page:1,
          size:100 //约等于查出全部数据
        }
      }).then(function(response){ //也可以写成(response) =>
                //拿到响应的数据
                const data = response.data;
                ebooks.value = data.content.list;
                //console.log(response);//打印响应结果
              });
    })


    return{
      ebooks,
      pagination:{
        onChange: (page: any) => {
          console.log(page);
        },
        pageSize: 3,
      },
      actions:[
        { type: 'StarOutlined', text: '156' },
        { type: 'LikeOutlined', text: '156' },
        { type: 'MessageOutlined', text: '2' },
      ],
    }
  },
  components: {

  },
});
</script>
<!-- scoped表示只在当前组件起作用 -->
<style scoped>
  .ant-avatar {
    width: 60px;
    height: 80px;
    line-height: 50px;
    border-radius: 8%;
    margin: 5px 0;
  }
</style>
