<template>
   <div>
      <el-row>
        <el-col :span="24">
          <div class="card-header">
            <span>Contact</span>
          </div>
            <el-card align="left" class="box-card">
                <div>
                    <p><b>Principal Investigator: </b>Dr. Junfeng Xia</p>
                    <p>Institutes of Physical Science and Information Technology,Anhui University, Hefei, Anhui 230601, China.</p>
                    <p><b>Phone:</b> 86-551-63861990</p>
                    <p ><b>Email:</b> junfeng.xia@foxmail.com</p>
                    <p>If you have any comments, suggestions, questions, bug reports, etc, feel free to contact JianQiang Nie (<a href="mailto:jianqiangnie@163.com">jianqiangnie@163.com</a>).</p>
                </div>
            </el-card>
        </el-col>
    </el-row>
   </div>
</template>
<script>
export default {
   data() {
     return {}
   },
   created(){},
   methods:{},
}
</script>
<style lang="scss" scoped>
.box-card {
    width: 1000px;
  }

// 卡片头部
.card-header {
  background-color: #c8e0ee; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}
</style>
