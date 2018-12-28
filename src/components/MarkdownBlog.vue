<template>
  <div id="markdownBlog">
    <mavon-editor
      ref="md"
      @change="setContent"
      v-model="content"
      @imgAdd="$imgAdd"
      @imgDel="$imgDel"
    ></mavon-editor>
  </div>
</template>
<script>
import { mavonEditor } from "mavon-editor";
import "mavon-editor/dist/css/index.css";
export default {
  name: "markdownBlog",
  props: {
    content: String
  },
  data() {
    return {
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        subfield: true, // 是否需要分栏
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: true // 帮助
      },
      content:""
    };
  },

   methods: {
    setContent: function() {
      this.$emit('transferContent',this.content);
      //alert(this.content);
    }
  },

  components: {
    mavonEditor
  }
};
</script>
<style>
#editor {
  margin: auto;
  width: 80%;
  height: 580px;
  margin-top: 5rem;
}
</style>