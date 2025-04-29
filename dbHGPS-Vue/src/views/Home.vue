<template>
<div>
  <div class="contact-container">
    <el-row>
      <el-col :span="24">
        <div class="card-header">
            <span>Welcome to dbHGPS</span>
        </div>
        <!-- 卡片1 -->
        <el-card align="left" class="box-card">
          <div>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The <font color="#FF0000"><b>dbHGPS</b></font>  <b>(Database of Human Genome Polyadenylation Signal) </b>
              is a database focused on polyadenylation signals in the human genome. 
              The database aims to collect, organize, and provide information on polyadenylation signals in the human genome,
              offering a comprehensive and reliable resource for scientific researchers.
              Polyadenylation is an essential step in the maturation of eukaryotic mRNA, where a poly(A) tail is added to the 3' end of the transcript.  
              This process is guided by specific PAS sequences, typically located 10-40 nucleotides upstream of the cleavage site. 
              Accurate identification and annotation of PAS regions are crucial  for understanding gene expression regulation and the impact of PAS mutations on human diseases.
              To promote research on the role of polyadenylation signaling in various biological processes and diseases.
               We built a continuously updated, more comprehensive pas database on the human genome to provide a highly reliable data source.
            </p>
            <p>Features:</p>
            <ul>
              <li>
              <b>Comprehensive Data Collection: </b>  
              The dbHGPS integrates data from multiple public sources, including but not limited to published research papers, 
              genomic databases, and bioinformatics resources. By comprehensively utilizing this information, &nbsp;&nbsp;
              the dbHGPS provides a comprehensive and rich dataset on polyadenylation signals in the human genome, 
              offering a reliable resource foundation for scientific research.
            </li>
            <li>
              <b>High-quality Data Curation: </b>  
              The data in the database has undergone standardized preprocessing, rigorous screening, and careful curation, 
              ensuring the quality and reliability of the data.
            </li>
            <li>
              <b>Easy Access and Usability: </b>
              The dbHGPS provides a user-friendly web interface, allowing users to easily search,
               browse, and download the data they need.
            </li>
            </ul>
            <img src="~@/assets/imgs/home2.jpg" class="image" width="99%" />
            <p> <b>Access and Availability:</b> </p>
            dbHGPS is freely available to the research community and can be accessed through a user-friendly web interface. Detailed usage documentation is provided, 
            including guidelines for data download and analysis, to facilitate seamless integration of this database into research workflows.
            We welcome researchers to explore dbHGPS and contribute to the deepening understanding of the mechanisms of polyadenylation and their impacts on human health and disease.
          </div>
        </el-card>
        <!-- 卡片下方 -->
      </el-col>
    </el-row>
    <!-- 结束 -->
  </div>
  <!-- 第二个div -->
  <!-- <div class="apa-container">
   <el-card>
      <div slot="header" class="custom-header">
        <p><b>APA Events</b></p>
        <p>APA events in different species</p>
      </div>
      <div>
         <img src="/imgs/1.png" class="image-card"/>
      </div>
    </el-card>

    <el-card style="margin-left: 20px;">
      <div slot="header" class="custom-header">
        <p><b>PAS</b></p>
        <p>Ploy(A) sites of each gene</p>
      </div>
      <div>
         <img src="/imgs/2.png" class="image-card"/>
      </div>
    </el-card>

    <el-card style="margin-left: 20px;">
      <div slot="header" class="custom-header">
        <p><b>Motif</b></p>
        <p>APA motifs near PAS</p>
      </div>
      <div>
         <img src="/imgs/3.png" class="image-card"/>
      </div>
    </el-card>
  </div> -->
</div>
</template>
<script>
export default {
  created() {},
  methods: {},
};
</script>
<style lang="scss" scoped>
.box-card {
  width: 1000px;
}
.clearfix {
  clear: both;
}

// 第二个div
.apa-container {
  margin-top: 20px;
  display: flex;
//   justify-content: space-around;
  width: 1000px;
//   align-items: center;
//   background-color: #ffd9b3;
//   padding: 20px;
//   border-radius: 10px;
}
// 卡片里面的图片的大小设置
.image-card {
   width: 100%;
   height: auto;
}

// 卡片标题头的宽度
.custom-header {
  padding: 0px 0px; /* 调整上下左右的内边距 */
}

.custom-header p {
  margin: 0; /* 移除段落的外边距 */
  font-size: 14px; /* 根据需要调整字体大小 */
}
// 卡片头部
.card-header {
  background-color: #06edce; /* 头部背景色 #11e3c7; */
  color: blank; /* 头部文字颜色 */
  padding: 10px; /* 头部内边距 */
  margin-top: 10px;
}


</style>
