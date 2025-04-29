<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <el-divider>
            <h3>Basic Search</h3>
          </el-divider>
          <!-- 表单 -->
          <el-form
            :inline="true"
            @submit.native.prevent="onBaseSearch"
            size="medium"
            :model="baseSearchForm"
          >
            <!-- 下拉框 -->
            <el-form-item>
              <el-select
                v-model="baseSearchForm.selectedType"
                placeholder="Select Type"
                @change="watchSelectedType"
              >
                <!-- Options go here -->
                <el-option label="Ensemble Gene ID" value="ensemble_gene_id"></el-option>
                <el-option label="NCBI Gene ID" value="ncbi_gene_id"></el-option>
                <el-option label="Gene Name" value="official_symbol"></el-option>
                <el-option label="Chromosome" value="chromosome"></el-option>
                <el-option label="PAS Signal" value="pas_signal"></el-option>
                <!-- <el-option label="Gene Name" value="geneName"></el-option> -->
              </el-select>
            </el-form-item>
            <!-- 输入框 -->
            <el-form-item>
              <el-input
                style="width: 550px"
                v-model="baseSearchForm.keyword"
                :placeholder="placeholderText"
              ></el-input>
            </el-form-item>
            <!-- 按钮 -->
            <el-form-item>
              <el-button
                type="primary"
                @click="onBaseSearch"
                icon="el-icon-search"
                >Search</el-button
              >
              <el-button @click="onBaseReset" type="info">Reset</el-button>
            </el-form-item>
          </el-form>
          <!-- 结果 -->
          <div class="base-search-form">
            <span>Output</span>
          </div>
          <!-- 表格内容-->
          <div>
            <el-table
              v-loading="listLoading"
              :data="tableData"
              :empty-text="customEmptyText"
              style="width: 100%"
              border
            >
            <el-table-column
                fixed
                prop="pasSignal"
                label="PAS Signal"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="startPas"
                label="Start PAS"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="endPas"
                label="End PAS"
                width="120"
                align="center"
              >
            </el-table-column>
              <el-table-column
                prop="chromosome"
                label="Chromosome"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="strand"
                label="Strand"
                width="80"
                align="center"
              >
              </el-table-column>
              
              <el-table-column
                prop="ensembleGeneId"
                label="Ensemble Gene Id"
                width="150"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="ncbiGeneId"
                label="NCBI Gene Id"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="officialSymbol"
                label="Gene Name"
                width="180"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="start"
                label="Start"
                width="100"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="end"
                label="End"
                width="100"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="taxId"
                label="Tax Id"
                width="80"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="synonyms"
                label="Synonyms"
                width="200"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="mapLocation"
                label="Map Location"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="officialFullName"
                label="Official FullName"
                width="400"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="geneBioType"
                label="Gene Biotype"
                width="120"
                align="center"
              >
              </el-table-column>

              <el-table-column
                prop="source"
                label="Source"
                width="550"
                align="center"
              >
              </el-table-column>
            </el-table>
          </div>
          <!-- 表格结束 -->
          <!-- 底部布局 -->
          <div
            style="
              display: flex;
              justify-content: space-between;
              margin-top: 10px;
            "
          >
          <!-- 下载按钮 -->
            <el-button
              :loading="downloadLoading1"
              type="primary"
              round
              size="small"
              icon="el-icon-download"
              @click="download"
              >download</el-button
            >
            <!-- 分页组件 -->
            <el-pagination
              background
              hide-on-single-page
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[10, 20, 30, 50]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination>
          </div>
          <!-- 分隔线 -->
          <el-divider>
            <h3>Advanced Search</h3>
          </el-divider>
          <!-- 第二个搜索表单-->
          <el-form
            :model="advancedSearchForm"
            @submit.native.prevent="onAdvancedSearch"
          >
            <el-form-item label="Chromosome">
              <el-input
                v-model="advancedSearchForm.chromosome"
                placeholder="Please enter chromosome. eg:chr2"
                style="width: 700px"
              ></el-input>
            </el-form-item>
            <el-form-item label="Start Position">
              <el-input
                v-model="advancedSearchForm.start_pas"
                placeholder="Please enter Start Position. eg:100000"
                style="width: 700px;"
              ></el-input>
              <!-- margin-left: 10px -->
            </el-form-item>
            <el-form-item label="End Position">
              <el-input
                v-model="advancedSearchForm.end_pas"
                placeholder="Please enter End Position. eg:867495"
                style="width: 700px; margin-left: 5px"
              ></el-input>
              <!-- margin-left: 34px -->
            </el-form-item>
            <el-form-item style="text-align: center">
              <el-button
                type="primary"
                size="medium"
                @click="onAdvancedSearch"
                icon="el-icon-search"
                >Search</el-button
              >
              <el-button @click="onAdvancedReset" size="medium" type="info"
                >Reset</el-button
              >
            </el-form-item>
          </el-form>
          <!-- 结果展示 -->
          <div class="anvanced-search-form">
            <span>Output</span>
          </div>
          <!-- 表格开始 -->
          <div>
            <el-table
              v-loading="listLoading"
              :data="tableData_advanced"
              :empty-text="customEmptyText"
              style="width: 100%"
              border
            >
            <el-table-column
                fixed
                prop="pasSignal"
                label="PAS Signal"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="startPas"
                label="Start PAS"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="endPas"
                label="End PAS"
                width="120"
                align="center"
              >
            </el-table-column>
              <el-table-column
                prop="chromosome"
                label="Chromosome"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="strand"
                label="Strand"
                width="80"
                align="center"
              >
              </el-table-column>
              
              <el-table-column
                prop="ensembleGeneId"
                label="Ensemble Gene Id"
                width="150"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="ncbiGeneId"
                label="NCBI Gene Id"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="officialSymbol"
                label="Gene Name"
                width="180"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="start"
                label="Start"
                width="100"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="end"
                label="End"
                width="100"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="taxId"
                label="Tax Id"
                width="80"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="synonyms"
                label="Synonyms"
                width="200"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="mapLocation"
                label="Map Location"
                width="120"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="officialFullName"
                label="Official FullName"
                width="400"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="geneBioType"
                label="Gene Biotype"
                width="120"
                align="center"
              >
              </el-table-column>

              <el-table-column
                prop="source"
                label="Source"
                width="550"
                align="center"
              >
              </el-table-column>
              <!-- <el-table-column
                fixed
                prop="id"
                label="ID"
                width="150"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="chromosome"
                label="Chromosome"
                width="110"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="position"
                label="Position"
                width="110"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="strand"
                label="Strand"
                width="80"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="geneId"
                label="Ensembl Gene ID"
                width="150"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="geneName"
                label="Gene Name"
                width="110"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="pasSignal"
                label="PAS"
                width="350"
                align="center"
              >
              </el-table-column> -->
            </el-table>
          </div>
          <!-- 表格结束 -->
          <!-- 底部布局 -->
          <div
            style="
              display: flex;
              justify-content: space-between;
              margin-top: 10px;
            "
          >
          <!-- 下载按钮 -->
            <el-button
              :loading="downloadLoading2"
              type="primary"
              round
              size="small"
              icon="el-icon-download"
              @click="download2"
              >download</el-button
            >
            <!-- 分页组件 -->
            <el-pagination
              background
              hide-on-single-page
              @size-change="handleSizeChange_advanced"
              @current-change="handleCurrentChange_advanced"
              :current-page="pageNum_advanced"
              :page-sizes="[10, 20, 30, 50]"
              :page-size="pageSize_advanced"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total_advanced"
            >
            </el-pagination>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import * as XLSX from "xlsx";

export default {
  data() {
    return {
      baseSearchForm: {
        selectedType: "",
        keyword: "",
      },
      advancedSearchForm: {
        chromosome: "",
        start_pas: "",
        end_pas: "",
        // geneName: "",
        // position: "",
      },
      // 基础检索
      pageSize: 10, // 每页显示的结果数量
      pageNum: 1, // 当前页码
      total: 0,
      tableData: [],
      // 当表格没有数据时候的提示词
      customEmptyText: "No data available",
      // 高级检索
      tableData_advanced: [],
      pageSize_advanced: 10, // 每页显示的结果数量
      pageNum_advanced: 1, // 当前页码
      total_advanced: 0,
      listLoading: false,
      // 下载
      downloadLoading1: false,
      downloadLoading2: false,
      filename: "",
      autoWidth: true,
      bookType: "xlsx",
    };
  },
  created() {},
  methods: {
    // 基础搜索提交
    onBaseSearch() {
      // 使用结构赋值从 this.baseSearchForm 对象中获取 selectedType 和 keyword 属性值
      const { selectedType, keyword } = this.baseSearchForm;
      // 检查 type 和 keyword 是否存在值
      if (!selectedType || !keyword) {
        this.$message.warning(
          "Please select a search type and enter a keyword"
        );
        return;
      }
      // 将pageSize和currentPage添加到baseSearchForm对象中
      this.baseSearchForm.pageSize = this.pageSize;
      this.baseSearchForm.pageNum = this.pageNum;
      // this.listLoading = true; // 加载效果                  http://localhost:8022/hgps/searchByKeyword?keyword=HBB
      // 发送 POST 请求到 /search 接口,并将 params 对象作为请求体  http://121.196.184.58:8022/hgps/baseSearch
      this.$axios
        .post(" /hgps/baseSearch", this.baseSearchForm)
        .then((response) => {
          this.tableData = response.data.data.records;
          this.total = response.data.data.total;
          if (response.data.data.records.length == 0) {
            this.$message.success("no data searched");
            // this.listLoading = false; // 加载效果关闭
            return;
          }
          // this.listLoading = false; // 加载效果关闭
        })
        .catch((error) => {
          console.error("Error:", error);
          this.$message.error("error");
        });
    },
    // 基础搜索重置
    onBaseReset() {
      this.baseSearchForm.selectedType = ""; // 重置下拉选择框
      this.baseSearchForm.keyword = ""; // 重置输入框内容
      this.tableData = "";
    },
    // 高级搜索提交
    onAdvancedSearch() {
      const params = JSON.parse(JSON.stringify(this.advancedSearchForm));
      const hasValue = Object.values(params).some((value) => value !== "");
      if (!hasValue) {
        this.$message.warning("Please enter at least one search keyword");
        return;
      }
      this.advancedSearchForm.pageSize = this.pageSize_advanced;
      this.advancedSearchForm.pageNum = this.pageNum_advanced;
      this.$axios
        .post("/hgps/advancedSearch", this.advancedSearchForm)
        .then((response) => {
          this.tableData_advanced = response.data.data.records;
          this.total_advanced = response.data.data.total;
          if (response.data.data.records.length == 0) {
            this.$message.success("no data searched");
            return;
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          this.$message.error("error");
        });
    },
    // 高级搜索重置
    onAdvancedReset() {
      this.advancedSearchForm.chromosome = "";
      this.advancedSearchForm.geneName = "";
      this.advancedSearchForm.position = "";
      this.tableData_advanced = "";
    },
    // 基础搜索(页面大小改变)
    handleSizeChange(val) {
      this.pageSize = val;
      this.onBaseSearch();
    },
    // 基础搜索（当前页码改变）
    handleCurrentChange(val) {
      this.pageNum = val;
      this.onBaseSearch();
    },
    // 高级搜索(页面大小改变)
    handleSizeChange_advanced(val) {
      this.pageSize_advanced = val;
      this.onAdvancedSearch();
    },
    // 高级搜索（当前页码改变）
    handleCurrentChange_advanced(val) {
      this.pageNum_advanced = val;
      this.onAdvancedSearch();
    },
    // 下载(基础下载)
    download() {
      let jsonData = this.tableData.map((row) => ({
        PAS_Signal: row["pasSignal"],
        Start_PAS: row["startPas"],
        End_PAS:row["endPas"],
        Chromosome:row["chromosome"],
        Strand:row["strand"],
        Ensemble_Gene_Id:row["ensembleGeneId"],
        NCBI_Gene_Id:row["ncbiGeneId"],
        Official_Symbol:row["officialSymbol"],
        Start:row["start"],
        End:row["end"],
        Tax_Id:row["taxId"],
        Synonyms:row["synonyms"],
        MapLocation:row["mapLocation"],
        Official_Full_Name:row["officialFullName"],
        GeneBioType:row["geneBioType"],
        Source:row["source"],
      }));
      // let jsonData = this.tableData
      let excelData = XLSX.utils.json_to_sheet(jsonData);
      var wb = XLSX.utils.book_new(); 

      XLSX.utils.book_append_sheet(wb, excelData, "sheet");

      XLSX.writeFile(wb, "export_data.xlsx");
      console.log("导出数据成功");
      // 提示下载成功
      this.$message({
        message: "Data download success",
        type: "success",
      });
    },
    // 下载（高级下载）
    download2() {
      let jsonData = this.tableData_advanced.map((row) => ({
        PAS_Signal: row["pasSignal"],
        Start_PAS: row["startPas"],
        End_PAS:row["endPas"],
        Chromosome:row["chromosome"],
        Strand:row["strand"],
        Ensemble_Gene_Id:row["ensembleGeneId"],
        NCBI_Gene_Id:row["ncbiGeneId"],
        Official_Symbol:row["officialSymbol"],
        Start:row["start"],
        End:row["end"],
        Tax_Id:row["taxId"],
        Synonyms:row["synonyms"],
        MapLocation:row["mapLocation"],
        Official_Full_Name:row["officialFullName"],
        GeneBioType:row["geneBioType"],
        Source:row["source"],
      }));
      // let jsonData = this.tableData_advanced;
      let excelData = XLSX.utils.json_to_sheet(jsonData);
      var wb = XLSX.utils.book_new(); 

      XLSX.utils.book_append_sheet(wb, excelData, "sheet");

      XLSX.writeFile(wb, "export_data.xlsx");
      console.log("导出数据成功");
      // 提示下载成功
      this.$message({
        message: "Data download success",
        type: "success",
      });
    },
  },
  // 计算属性：提示词
  computed: {
    placeholderText() {
      switch (this.baseSearchForm.selectedType) {
        case "ensemble_gene_id":
          return "Please enter Ensemble Gene ID as the search keyword; eg: ENSG00000117620";
        case "ncbi_gene_id":
          return "Please enter NCBI Gene ID as the search keyword; eg: 23443";
        case "official_symbol":
          return "Please enter Gene Name as the search keyword; eg: HBB";
        case "chromosome":
          return "Please enter Chromosome as the search keyword; eg: chr1";
        case "pas_signal":
          return "Please enter PAS Signal as the search keyword; eg: AAUAAA";
        default:
          return "Please select the Select Type and then enter the keyword";
      }
    },
  },
  // 监听器：监听下拉框的变化pas
  // 当下拉框的值改变时，重置输入框的内容
  watch: {
    "baseSearchForm.selectedType": function (newVal, oldVal) {
      this.baseSearchForm.keyword = ""; // 重置输入框内容
    },
  },
};
</script>

<style lang="scss" scoped>
.search-card {
  width: 1000px;
  margin-bottom: 20px;
}

.base-search-form {
  background-color: #ff9d9d; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}

.anvanced-search-form {
  background-color: #c8f4f0; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}

.el-table__empty-text {
  height: 100px; /* 调整为你期望的高度 */
  line-height: 100px; /* 与高度一致,使文本垂直居中 */
}
</style>
