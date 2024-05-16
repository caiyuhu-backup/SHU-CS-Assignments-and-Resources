<template>
  <el-row :gutter="20">
    <el-col :span="20" :push="2">
      <el-card>
        <el-upload
            class="upload-demo"
            :limit="100"
            drag
            action="http://localhost/csky/upload/uploadFile"
            :headers="{param: 'file'}"
            multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <!--      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>-->
        </el-upload>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Upload"
}
</script>

<style scoped>
.upload-demo {
  text-align: center;
}
</style>