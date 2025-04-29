<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div>
          <div class="card-header-archive">
            <span>Archive</span>
          </div>
          <!-- 内容 -->
          <el-card align="left" class="box-card">
            <div>
              <el-timeline>
                <el-timeline-item
                  timestamp="2024/07/15"
                  placement="top"
                  color="#0bbd87"
                >
                  <el-card>
                    <h4 style="margin-top: 0px; margin-bottom: 0px">
                      Version 1.0
                    </h4>
                    <p>Fresh —— May 15,2024</p>
                    <p>Date:2024-03-01 to 2024-07-15</p>
                    <p>Details:
                      We obtained pA sites of the human genome from public databases or publicly available articles. 
                      After uniformly processing the pA sites, we matched them with the PAS signals we obtained through scripts,
                      ultimately obtaining the data for the database. In version 1.0, we provided pages such as Home, Search, Download, Visualization, etc.,
                      completing functions such as data display, basic search, advanced search, visualization, download, etc., along with detailed explanations.
                    </p>
                  </el-card>
                </el-timeline-item>
                <el-timeline-item timestamp="xxxx/xx/xx" placement="top">
                  <el-card>
                    <h4 style="margin-top: 0px; margin-bottom: 0px">
                      Version 2.0
                    </h4>
                    <p>Operation in progress</p>
                  </el-card>
                </el-timeline-item>
              </el-timeline>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <!-- 分割线-->
    <el-divider></el-divider>
    <!-- 第二个row -->
    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <div class="card-header-summary">
          <span>pA site data source summary</span>
        </div>

        <div>
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column
              prop="source"
              label="Source"
              width="210"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="reference"
              label="Reference"
              width="680"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="version"
              label="Version"
              width="80"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="paSiteNumber"
              label="Pa Site Number"
              width="180"
              align="center"
            >
            </el-table-column>
            <!-- <el-table-column label="Download" width="180" align="center">
              <template slot-scope="scope">
                <el-button
                  type="success"
                  size="mini"
                  icon="el-icon-download"
                  @click="download(scope.row.downloadLink)"
                  >Download</el-button
                >
              </template>
            </el-table-column> -->
          </el-table>
        </div>
      </el-col>
    </el-row>

    <!-- 分割线 -->
    <!-- <el-divider></el-divider> -->
    <!-- 第二个row  -->
    <!-- <el-row style="margin-top: 10px">
      <el-col :span="24">
        <div class="card-header-summary">
          <span>PAS signal summary</span>
        </div>

        <div>
          <el-table :data="tableData_PAS" border style="width: 100%">
            <el-table-column
              prop="source"
              label="Source"
              width="210"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="reference"
              label="Reference"
              width="680"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="version"
              label="Version"
              width="80"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="paSiteNumber"
              label="Pa Site Number"
              width="180"
              align="center"
            >
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>  -->

    <!-- 分割线-->
    <el-divider></el-divider>
    <!-- 第三个row -->
    <el-row>
      <el-col :span="24">
        <div class="card-header-how-to-query">
          <span>How to query</span>
        </div>
        <div>
          <el-card align="left" class="box-card">
            <div>
              <span style="font-size: 20px;color: red;"><b>Basic Search</b></span>
              <img src="~@/assets/imgs/page_1.png" class="image" width="99%" />
              <p>
                In basic search, users need to select the type of search keyword, supported types include ID, Chromosome, Ensembl ID, and Gene Name, and then enter the search keyword to perform the search.
                The search results are displayed in tabular form, users can paginate through the data, adjust the number of data entries displayed per page, and navigate to specific page numbers to view the data. 
                By clicking the download button in the bottom left corner, users can download the search results, which will be saved as an xlsx file.
              </p>
            </div>
            <div>
              <span style="font-size: 20px;color: red;"><b>Advanced Search</b></span>
              <img src="~@/assets/imgs/page_2.png" class="image" width="99%" />
              <p>
                In advanced search, users can enter the search conditions and click the search button to search.
                Support for multiple search criteria, including Gene Name also supports fuzzy query.
                The search results are displayed in tabular form, users can paginate through the data, adjust the number of data entries displayed per page, and navigate to specific page numbers to view the data. By clicking the download button in the bottom left corner, users can download the search results, which will be saved as an xlsx file.
              </p>
            </div>
            <div>
              <span style="font-size: 20px;color: red;"><b>Visualization of Gene & pA Site & PAS Signals</b></span>
              <img src="~@/assets/imgs/page_3.png" class="image" width="99%" />
              <p>
                On the Visualization page, users can input a gene name as a keyword to retrieve the number of pA sites corresponding to this gene and the types of PAS signals.
                Clicking the Plot button on the far right will display a tree diagram of gene-site-PAS in a pop-up window. 
                Through this tree diagram, users can clearly see the pA sites of the gene and their associated PAS signals. 
                Users can also download this tree diagram.
              </p>
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <!-- 分割线-->
    <el-divider></el-divider>
    <!-- 第四个row -->
    <el-row>
      <el-col :span="24">
        <div class="card-header-citation">
          <span>Citation</span>
        </div>
        <div>
          <el-card align="left" class="box-card">
            <div>
              <p>
                Please cite the following articles if you use dbHGPS in your research:
              </p>
              <p> 
                xxxxxxx
              </p>
              <!-- <p>论文的相关引用信息</p> -->
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 表格模拟数据
      tableData: [
        {
          source: "Ni et al.",
          reference: "Ni T, Yang Y Q, Hafez D, et al. Distinct polyadenylation landscapes of diverse human tissues revealed by a modified PA-seq strategy [J]. Bmc Genomics, 2013, 14(1): 615-615.",
          version: "GRCh37",
          paSiteNumber: "38,080",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_Ni_hg19.csv"
        },
        {
          source: "Dert et al.",
          reference: "Derti A, Garret P, MacIsaac K D, et al. A quantitative atlas of polyadenylation in five mammals [J].Genome Res, 2012, 22(6): 1173-1183.",
          version: "GRCh37",
          paSiteNumber: "345,719",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_Derti_hg19.csv"
        },
        {
          source: "polyADB3 database",
          reference:
            "Wang R, Nambiar R, Zheng D, & Tian B (2017). PolyA_DB 3 catalogs cleavage and polyadenylation sites identified by deep sequencing in multiple genomes. Nucleic Acids Research 46(D1):D315-D319.",
          version: "GRCh37",
          paSiteNumber: "311,594",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_polyAdb_hg19.csv"
        },
        {
          source: "PolyASite 2.0",
          reference:
            "Herrmann, C. J. et al. PolyASite 2.0: a consolidated atlas of polyadenylation sites from 3′ end sequencing. Nucleic Acids Res 48, D174–D179 (2020).",
          version: "GRCh38",
          paSiteNumber: "569,005",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_polyASite2.0_hg38.csv"
        },
        {
          source: "scAPAdb database",
          reference:
            "Sheng Zhu, Qiwei Lian, Wenbin Ye, Wei Qin, Zhe Wu, Guoli Ji, Xiaohui Wu*, scAPAdb: a comprehensive database of alternative polyadenylation at single-cell resolution, Nucleic Acids Research, Volume 50, Issue D1, 7 January 2022, Pages D365–D370.",
          version: "GRCh38",
          paSiteNumber: "264,745",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_scAPAdb_hg38.csv"
        },
        {
          source: "Leung et al.",
          reference: "Michael K K Leung, Andrew Delong, Brendan J Frey, Inference of the human polyadenylation code, Bioinformatics, Volume 34, Issue 17, September 2018, Pages 2889–2898.",
          version: "GRCh37",
          paSiteNumber: "92,218",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_MichaelK_hg19.csv"
        },
        {
          source: "PolyASite 3.0 database",
          reference: "Moon Y, Herrmann C J, Mironov A, et al. PolyASite v3. 0: a multi-species atlas of polyadenylation sites inferred from single-cell RNA-sequencing data [J]. Nucleic Acids Research, 2024: gkae1043.",
          version: "GRCh38",
          paSiteNumber: "121,870",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_MichaelK_hg19.csv"
        },
        {
          source: "GENCODE PolyA feature",
          reference: "Harrow J, Frankish A, Gonzalez J M, et al. GENCODE: the reference human genome annotation for The ENCODE Project [J]. Genome research, 2012, 22(9): 1760-74.",
          version: "GRCh38",
          paSiteNumber: "51,523",
          downloadLink: "https://picgo-typora-images.oss-cn-beijing.aliyuncs.com/dbHGPS/pA/pA_MichaelK_hg19.csv"
        },
      ],
    };
  },
  created() {},
  methods: {
    // 下载
    download(link) {
      window.location.href = link;
      this.$message({
        message: "Download successfully",
        type: "success",
      });
    },
  },
};
</script>
<style lang="scss" scoped>
// 卡片头部Archive
.card-header-archive {
  background-color: #f2d19c; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}

// 卡片头部Summary
.card-header-summary {
  background-color: #cdedb3; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}
// 卡片头部How to query
.card-header-how-to-query {
  background-color: #bdf1ed; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}

// 卡片头部Citation
.card-header-citation {
  background-color: #f79db3; /* 头部背景色 */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}
</style>
