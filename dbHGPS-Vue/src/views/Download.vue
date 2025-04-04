<template>
  <div>
    <!-- 第一部分 -->
    <el-row>
      <el-col :span="24">
        <el-divider content-position="left">
          <h3>
            Download pA Site Data
            <el-popover
              placement="top-start"
              title="Data version description of this website"
              width="400"
              trigger="hover"
              :content="popoverContent"
            >
              <i
                slot="reference"
                class="el-icon-s-comment"
                style="color: red"
              ></i>
            </el-popover>
          </h3>
        </el-divider>
        <div>
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column
              prop="organisms"
              label="Organisms"
              width="180"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="number"
              label="Number"
              width="180"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="version"
              label="Genome version"
              align="center"
            >
            </el-table-column>
            <el-table-column label="Download" align="center">
              <template slot-scope="scope">
                <el-button
                  type="success"
                  size="mini"
                  icon="el-icon-download"
                  @click="download(scope.row.downloadLink)"
                  >Download</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
    <!-- 第二部分 -->
    <el-row style="margin-top: 30px">
      <el-col :span="24">
        <el-divider content-position="left">
          <h3>
            Download genome and annotation
            <el-popover
              placement="top-start"
              title="Data version description of this website"
              width="400"
              trigger="hover"
              :content="popoverContent"
            >
              <i
                slot="reference"
                class="el-icon-s-comment"
                style="color: red"
              ></i>
            </el-popover>
          </h3>
        </el-divider>
        <div>
          <el-table :data="tableData2" border style="width: 100%">
            <el-table-column
              prop="organisms"
              label="Organisms"
              width="180"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="version"
              label="Version"
              width="180"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="genome"
              label="Genome"
              width="420"
              align="center"
            >
              <template slot-scope="scope">
                <!-- <a
                  :href="`https://ftp.ensembl.org/pub/grch37/current/fasta/homo_sapiens/dna/Homo_sapiens.GRCh37.dna.primary_assembly.fa.gz`"
                  class="custom-link"
                >
                  <span style="font-weight: bold">{{ scope.row.genome }}</span>
                </a> -->
                <a :href="scope.row.genomeLink" class="custom-link">
                  <span style="font-weight: bold">{{ scope.row.genome }}</span>
                </a>
              </template>
            </el-table-column>
            <el-table-column
              prop="annotation"
              label="Annotation"
              width="310"
              align="center"
            >
              <template slot-scope="scope">
                <!-- <a
                  :href="`http://ftp.ensembl.org/pub/grch37/release-87/gtf/homo_sapiens/Homo_sapiens.GRCh37.87.gtf.gz`"
                  class="custom-link"
                >
                  <span style="font-weight: bold">{{
                    scope.row.annotation
                  }}</span>
                </a> -->
                <a :href="scope.row.annotationLink" class="custom-link">
                  <span style="font-weight: bold">{{
                    scope.row.annotation
                  }}</span>
                </a>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
    <!-- 第三部分 -->
    <el-row>
      <el-col :span="24">
        <el-divider content-position="left">
          <h3>Download PAS Signal</h3>
        </el-divider>
        <div>
          <el-table :data="tableData3" border style="width: 100%">
            <el-table-column
              prop="number"
              label="PAS Number"
              width="120"
              align="center"
            >
            </el-table-column>
            <el-table-column label="PAS List" width="750" align="center">
              <template slot-scope="scope">
                <div class="sequence-container">
                  <el-tag
                    v-for="item in scope.row.sequenceArray"
                    :key="item"
                    type="success"
                    style="margin-right: 5px; margin-top: 5px"
                  >
                    {{ item }}
                  </el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="Download" width="130" align="center">
              <el-button
                type="success"
                size="mini"
                icon="el-icon-download"
                @click="downloadPAS"
                >Download</el-button
              >
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 表格1
      tableData: [
        {
          organisms: "Homo_sapiens",
          number: "637446",
          version: "hg38 (GRCh38)",
          downloadLink:"https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/PAS_data_hg38.csv",
        },
        {
          organisms: "Homo_sapiens",
          number: "637248",
          version: "hg19 (GRCh37)",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/PAS_data_hg19.csv",
        },
      ],
      // 表格2
      tableData2: [
        // {
        //   organisms: "Homo_sapiens",
        //   version: "	GRCh37",
        //   genome: "Homo_sapiens.GRCh37.dna.primary_assembly.fa",
        //   annotation: "Homo_sapiens.GRCh37.111.gtf.gz",
        //   genomeLink: `https://ftp.ensembl.org/pub/grch37/release-111/fasta/homo_sapiens/dna/Homo_sapiens.GRCh37.dna.primary_assembly.fa.gz`,
        //   annotationLink: `http://ftp.ensembl.org/pub/grch37/release-111/gtf/homo_sapiens/Homo_sapiens.GRCh37.87.gtf.gz`,
        // },
        {
          organisms: "Homo_sapiens",
          version: "	GRCh38",
          genome: "Homo_sapiens.GRCh38.dna.primary_assembly.fa",
          annotation: "Homo_sapiens.GRCh38.110.gtf.gz",
          genomeLink: `https://ftp.ensembl.org/pub/release-110/fasta/homo_sapiens/dna/Homo_sapiens.GRCh38.dna.primary_assembly.fa.gz`,
          annotationLink: `https://ftp.ensembl.org/pub/release-110/gtf/homo_sapiens/Homo_sapiens.GRCh38.110.gtf.gz`,
        },
      ],
      tableData3: [
        {
          number: "25 kinds",
          sequenceArray: [
            "AAUAAA",
            "AUUAAA",
            "UUUAAA",
            "AAGAAA",
            "AAAAAG",
            "UUAAAU",
            "AAAACA",
            "UAUAAA",
            "AACAAA",
            "AAUAUA",
            "AAUAAU",
            "AAUGAA",
            "AGUAAA",
            "UUAAAG",
            "AAACAU",
            "AAUACA",
            "CAUAAA",
            "AUUAUA",
            "GAUAAA",
            "ACUAAA",
            "AAUAGA",
            "AAUAAG",
            "AUUACA",
            "AACAAG",
            "AAUAAC",
          ],
        },
      ],
      popoverContent:
        "This website uses the  hg38 (GRCh38) version. in order to facilitate the use of other researchers, this website also provides the hg19 (GRCh19) version for researchers to choose.",
    };
  },
  created() {},
  methods: {
    // 下载(基础下载)
    download(link) {
      window.location.href = link;
      this.$message({
        message: "Download successfully",
        type: "success",
      });
    },
    // 下载PAS
    downloadPAS() {
      // 下载
      window.location.href =
        "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/PAS.txt.zip";
      // 提示下载成功
      this.$message({
        message: "Download successfully",
        type: "success",
      });
    },
  },
};
</script>
<style lang="scss" scoped>
// 超链接
.custom-link {
  font-size: 16px;
  text-decoration: none;
}
// PAS信号的显示
.sequence-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(100px, 1fr)); /* 调整标签的宽度 */
  grid-gap: -15px; /* 调整标签之间的间距 */
  align-items: center;
  justify-content: center;
}
.sequence-container .el-tag {
  text-align: center;
  width: 80px; /* 设置标签的固定宽度 */
  height: 30px; /* 设置标签的固定高度 */
  line-height: 30px; /* 使文本垂直居中 */
}
</style>
