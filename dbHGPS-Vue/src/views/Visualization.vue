<template>
  <div>
    <!-- 第一块 -->
    <el-row>
      <el-col :span="24">
        <div class="card-header-chart">
          <span>The relative distribution of PAS in the human genome</span>
        </div>
        <el-card align="left" class="box-card">
          <div
            id="chart"
            style="
              width: 1000px;
              height: 400px;
              display: flex;
              justify-content: center;
              align-items: center;
            "
          ></div>
        </el-card>
      </el-col>
    </el-row>
    <div style="margin-top: 10px; margin-bottom: 10px">
      <el-divider></el-divider>
    </div>

    <!-- 第二块 -->
    <el-row>
      <el-col :span="24">
        <!-- 头部 -->
        <div class="card-header-plot">
          <span>Visualization of Gene & PAS Signals</span>
        </div>
        <!-- 卡片 -->
        <el-card align="left" class="box-card">
          <!-- 内容:表单 -->
          <div>
            <!-- 根据基因名,检索出基因对应的pA位点数目以及PAS信号数目 -->
            <el-form
              :inline="true"
              @submit.native.prevent="onSubmit"
              size="medium"
              :model="searchForm"
            >
              <el-form-item>
                <el-input
                  style="width: 300px"
                  v-model="searchForm.keyword"
                  placeholder="Please enter Gene Name. eg:HBB"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="onSubmit"
                  icon="el-icon-search"
                  >Search</el-button
                >
                <el-button type="info" @click="onReset">Reset</el-button>
              </el-form-item>
            </el-form>
          </div>
          <el-divider content-position="left">Output</el-divider>
          <div>
            <el-table :data="tableData" border style="width: 100%">
              <el-table-column
                prop="ensemble_gene_id"
                label="Ensembl Gene ID"
                width="180"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="ncbi_gene_id"
                label="NCBI Gene ID"
                width="150"
                align="center"
              >
              </el-table-column>
              <!-- <el-table-column prop="position" label="Position" align="center">
              </el-table-column>
              <el-table-column prop="strand" label="Strand" align="center">
              </el-table-column>
              <el-table-column prop="start" label="Start" align="center">
              </el-table-column>
              <el-table-column prop="end" label="End" align="center">
              </el-table-column>
              <el-table-column prop="width" label="Width" align="center">
              </el-table-column> -->
              <el-table-column
                prop="geneName"
                label="Gene Name"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="pasCount"
                label="PAS Count"
                width="100"
                align="center"
              >
              </el-table-column>
              <!-- PAS 种类 -->
              <el-table-column
                prop="pasList"
                label="PAS Signals"
                width="400"
                align="center"
              >
                <template slot-scope="scope">
                  <div class="sequence-container">
                    <el-tag
                      v-for="item in scope.row.pasList"
                      :key="item"
                      type="success"
                      style="margin-right: 5px; margin-top: 5px"
                    >
                      {{ item }}
                    </el-tag>
                  </div>
                </template>
              </el-table-column>
              <el-table-column
                fixed="right"
                label="Plot"
                width="100"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button
                    @click="handleClick(scope.row.geneName)"
                    type="success"
                    size="small"
                  >
                    Plot
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 绘图的弹框-->
    <el-dialog
      title="Visualization of Gene and PAS signals"
      :visible.sync="dialogVisible"
      width="60%"
      center
      :before-close="handleClose"
    >
      <div
        id="treeChart"
        style="
          width: 100%;
          height: 500px;
          /* display: flex;
          justify-content: center;
          align-items: center; */
          /* margin: 0 auto; */
          /* margin: 0; */
          /* PALMD */
          /* margin-left: 20px;
          margin-right: 20px; */
        "
      ></div>
      <!-- <p>
        <b>Note:</b>For the same pA site, if the same PAS appears, their genomic
        locations are different.
      </p> -->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false"
          >close</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
// 引入echarts
// var echarts = require("echarts");
import * as echarts from '../static/echarts.min.js'; // 引入echarts
export default {
  data() {
    return {
      dialogVisible: false,
      searchForm: {
        keyword: "",
      },
      // 表格模拟数据
      tableData: [],
      chartData: null, // 柱状图数据
      chartData:[
      { value: 127647, name: 'AAUAAA' },
      { value: 54038, name: 'AUUAAA' },
      { value: 42077, name: 'UUUAAA' },
      { value: 38313, name: 'AAGAAA' },
      { value: 35226, name: 'AAAAAG' },
      { value: 29835, name: 'UUAAAU' },
      { value: 29147, name: 'AAAACA' },
      { value: 27252, name: 'UAUAAA' },
      { value: 23409, name: 'AACAAA' },
      { value: 22135, name: 'AAUAUA' },
      { value: 21514, name: 'AAUAAU' },
      { value: 21110, name: 'AAUGAA' },
      { value: 19159, name: 'AGUAAA' },
      { value: 18384, name: 'UUAAAG' },
      { value: 17441, name: 'AAACAU' },
      { value: 16783, name: 'AAUACA' },
      { value: 13864, name: 'CAUAAA' },
      { value: 13180, name: 'AUUAUA' },
      { value: 12203, name: 'GAUAAA' },
      { value: 12131, name: 'ACUAAA' },
      { value: 9862, name: 'AAUAGA' },
      { value: 8992, name: 'AAUAAG' },
      { value: 8409, name: 'AUUACA' },
      { value: 7713, name: 'AACAAG' },
      { value: 7622, name: 'AAUAAC' },
      ],
      treeChartData: null, // 树状图数据
    };
  },
  created() {
    // this.fetchChartData();
  },
  mounted() {
    this.initChart();
  },
  methods: {
    // 搜索提交
    onSubmit() {
      // 使用结构赋值从 this.searchForm 对象中获取 keyword 属性值
      const { keyword } = this.searchForm;
      // 检查 type 和 keyword 是否存在值
      if (!keyword) {
        this.$message.warning("Please enter your search keyword");
        return;
      }
      // 发送 POST 请求到 /searchByKeyword 接口,并将 params 对象作为请求体  http://121.196.184.58
      this.$axios
        .get("/hgps/searchByKeyword", {
          params: {
            keyword: this.searchForm.keyword,
          },
        })
        .then((response) => {
          this.tableData = response.data.data;
          if (response.data.data.length == 0) {
            this.$message.success("no data searched");
            return;
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          this.$message.error("error");
        });
    },
    // 搜索重置
    onReset() {
      this.searchForm.keyword = ""; // 重置输入框内容
      this.tableData = "";
    },
    // 弹框关闭
    handleClose(done) {
      this.$confirm("Confirm close ?")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    // 展示图像
    handleClick(geneName) {
      this.dialogVisible = true;
      // 发送请求
      this.$axios
        .get("/hgps/getSiteByGeneName", {
          params: {
            geneName: geneName,
          },
        })
        .then((response) => {
          this.treeChartData = response.data.data;

          this.initTreeChart(); // 在这里调用(关键点)
        })
        .catch((error) => {
          console.error("Error:", error);
          this.$message.error("error");
        });
    },

    //  获取饼状图数据
    // fetchChartData() {
    //   this.$axios
    //     .get("http://localhost:8000/pas_type_number")
    //     .then((response) => {
    //       this.chartData = response.data.data;
    //       this.initChart(); // 在这里调用 initChart(关键点)
    //     })
    //     .catch((error) => {
    //       this.$message.error("Fetch data error");
    //       console.log(error);
    //       return;
    //     });
    // },
    // 初始化图表（饼状图）
    initChart() {
      const chart = echarts.init(document.getElementById("chart"));
      // 检查chartData是否存在
      if (!this.chartData) {
        this.$message.error("No chart data available");
        return;
      }
      // 自定义颜色palette
      var colorPalette = [
        "#8a98dc",
        "#93c572",
        "#61A0A8",
        "#D48265",
        "#91C7AE",
        "#749F83",
        "#CA8622",
        "#BDA29A",
        "#e7211a",
        "#546570",
        "#C4CCD3",
        "#40A0A0",
        "#96A83F",
        "#835D64",
        "#3B61A8",
        "#AF5D63",
        "#354356",
        "#C6B9D2",
        "#e59549",
        "#d4b7d8",
        "#f2b2a6",
        "#c2d0ae",
        "#cd6765",
        "#b66ce1",
        "#ecca4d",
        "#d4b7d8",
        "#34da7c",
        "#da3287",
        "#00c5cd",
        "#00cc99",
        "#00ced1",
        "#482683",
        "#da3287",
        "#ff1493",
        "#F37021",
        "#7bb661",
        "#ffb3de",
        "#c2cc39",
        "#ec4347",
        "#ef5b2f",
      ];
      // 配置项
      const option = {
        // title: {
        //   text: "Referer of a Website",
        //   subtext: "Fake Data",
        //   left: "center",
        // },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
          show: false,
        },
        series: [
          {
            // name: "PAS",
            type: "pie",
            radius: "80%",
            data: this.chartData,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
            itemStyle: {
              // 通过回调函数设置每个柱子的颜色
              color: function (params) {
                return colorPalette[params.dataIndex];
              },
            },
            label: {
              normal: {
                show: true,
                textStyle: {
                  fontSize: 16,
                },
              },
            },
          },
        ],
      };
      chart.setOption(option);
    },

    // 获取柱状图数据
    // fetchChartData() {
    //   this.$axios
    //     .get("http://139.196.51.181:8000/pas_type_number")
    //     .then((response) => {
    //       this.chartData = response.data.data;
    //       // console.log(this.chartData);
    //       // console.log(this.chartData.categories);
    //       // console.log(this.chartData.counts);
    //       this.initChart(); // 在这里调用 initChart(关键点)
    //     })
    //     .catch((error) => {
    //       this.$message.error("Fetch data error");
    //       console.log(error);
    //       return;
    //     });
    // },


    // 初始化图表(柱状图)
    // initChart() {
    //   const chart = echarts.init(document.getElementById("chart"));
    //   // 检查chartData是否存在
    //   if (!this.chartData) {
    //     this.$message.error("No chart data available");
    //     return;
    //   }
    //   // 自定义颜色palette
    //   var colorPalette = [
    //     "#8a98dc",
    //     "#93c572",
    //     "#61A0A8",
    //     "#D48265",
    //     "#91C7AE",
    //     "#749F83",
    //     "#CA8622",
    //     "#BDA29A",
    //     "#e7211a",
    //     "#546570",
    //     "#C4CCD3",
    //     "#40A0A0",
    //     "#96A83F",
    //     "#835D64",
    //     "#3B61A8",
    //     "#AF5D63",
    //     "#354356",
    //     "#C6B9D2",
    //     "#e59549",
    //     "#d4b7d8",
    //     "#f2b2a6",
    //     "#c2d0ae",
    //     "#cd6765",
    //     "#b66ce1",
    //     "#ecca4d",
    //     "#d4b7d8",
    //     "#34da7c",
    //     "#da3287",
    //     "#00c5cd",
    //     "#00cc99",
    //     "#00ced1",
    //     "#482683",
    //     "#da3287",
    //     "#ff1493",
    //     "#F37021",
    //     "#7bb661",
    //     "#ffb3de",
    //     "#c2cc39",
    //     "#ec4347",
    //     "#ef5b2f",
    //   ];
    //   // 配置项
    //   const option = {
    //     title: {
    //       text: "Visualization of the number of pA sites corresponding to PAS",
    //       left: "center", // 让标题内容水平居中
    //     },
    //     toolbox: {
    //       feature: {
    //         saveAsImage: {
    //           show: true,
    //           // 自定义提示文本
    //           title: "Save as Image",
    //           // 自定义按钮样式
    //           iconStyle: {
    //             borderColor: "red", // 边框颜色
    //             borderWidth: 2, // 边框宽度
    //             backgroundColor: "red", // 背景颜色
    //             // borderRadius: 5, // 设置圆角边框
    //             // backgroundColor: "#3498db", // 设置背景色为浅蓝色
    //             // borderColor: "#2980b9", // 设置边框色为深蓝色
    //             // shadowBlur: 10, // 添加阴影效果
    //             // borderWidth: 10, // 边框宽度设为 0
    //             // shadowColor: "rgba(0, 0, 0, 0.3)", // 阴影颜色
    //           },
    //           emphasis: {
    //             iconStyle: {
    //               backgroundColor: "blue", // 高亮时背景颜色
    //             },
    //           },
    //         },
    //       },
    //     },
    //     xAxis: {
    //       type: "category",
    //       data: this.chartData.categories,
    //       // 字体倾斜
    //       axisLabel: {
    //         rotate: 30, // 旋转角度
    //         fontWeight: "bold", // 加粗
    //         color: "#000000", // 设置为黑色
    //       },
    //     },
    //     yAxis: {
    //       type: "value",
    //     },
    //     series: [
    //       {
    //         type: "bar",
    //         data: this.chartData.counts,
    //         // 显示数值
    //         barWidth: 15, // 柱子宽度
    //         barMaxWidth: 30, // 最大柱子宽度
    //         // 显示数值
    //         label: {
    //           show: true,
    //           position: "top",
    //         },
    //         // 该柱子颜色
    //         itemStyle: {
    //           // 通过回调函数设置每个柱子的颜色
    //           color: function (params) {
    //             return colorPalette[params.dataIndex];
    //           },
    //         },
    //       },
    //     ],
    //   };
    //   chart.setOption(option);
    // },


    // 初始化图表(树图)
    initTreeChart() {
      var chartDom = document.getElementById("treeChart");
      var myChart = echarts.init(chartDom);

      // 检查treeChartData是否存在
      if (!this.treeChartData) {
        this.$message.error("No chart data available");
        return;
      }
      // 配置项
      const option = {
        // title: {
        //   text: "Visualization of the number of pA sites corresponding to PAS",
        //   left: "center", // 让标题内容水平居中
        // },
        tooltip: {
          trigger: "item",
          triggerOn: "mousemove",
        },
        toolbox: {
          feature: {
            saveAsImage: {
              show: true,
              // 自定义按钮样式
              iconStyle: {
                borderColor: "red", // 边框颜色
                borderWidth: 2, // 边框宽度
                backgroundColor: "red", // 背景颜色
              },
              emphasis: {
                iconStyle: {
                  backgroundColor: "blue", // 高亮时背景颜色
                },
              },
              // 自定义工具提示文本
              title: "Save as Image",
            },
          },
          right: "0%", // 控制工具箱的水平位置偏移
          bottom: "92%", // 控制工具箱的垂直位置偏移
          itemSize: 25, // 设置工具箱中每个工具的大小
        },
        series: [
          {
            type: "tree",
            data: [this.treeChartData],
            top: '1%',
            left: '7%',
            bottom: '1%',
            right: '40%',
            symbolSize: 10,

            roam: true, // 开启平移和缩放
            scaleLimit: { // 缩放范围
              min: 0.5, // 最小缩放比例
              max: 2 // 最大缩放比例
            },
            // 节点的样式
            itemStyle: {
              color: "#00c5cd",
            },
            // 树边的设置
            lineStyle: {
              color: "#f2a788", // 树图边的颜色
              width: 2, // 树图边的宽度
            },
            label: {
              position: "left",
              verticalAlign: "middle",
              align: "right",
              fontSize: 15,
            },
            leaves: {
              label: {
                position: "right",
                verticalAlign: "middle",
                align: "left",
              },
            },
            emphasis: {
              focus: "descendant",
            },
            expandAndCollapse: true,
            animationDuration: 550,
            animationDurationUpdate: 750,
          },
        ],
      };

      myChart.setOption(option);
    },
  },
};
</script>
<style lang="scss" scoped>
// 第一个绘图卡片头部
.card-header-chart {
  background-color: #8fc9c8; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}
// 第二个绘图卡片头部
.card-header-plot {
  background-color: #93c572; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
  margin-bottom: 10px;
}
// PAS信号的显示
.sequence-container {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}
</style>
