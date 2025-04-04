<template>
    <div class="container">
      <header>
        <div class="header">
          <a href="./">
            <img src="~@/assets/imgs/banner2.jpg" />
          </a>
          <nav class="navbar-container">
            <ul>
              <li class="navbar-item">
                <router-link to="/home" class="navbar-link">Home</router-link>
              </li>
              <li class="navbar-item">
                <router-link to="/search" class="navbar-link">Search</router-link>
              </li>
              <li class="navbar-item">
                <router-link to="/download" class="navbar-link">Download</router-link>
              </li>
              <li class="navbar-item">
                <router-link to="/visualization" class="navbar-link">Visualization</router-link>
              </li>
              <!-- <li class="navbar-item">
                <router-link to="/predict" class="navbar-link">Predict</router-link>
              </li> -->
              <li class="navbar-item">
                <router-link to="/about" class="navbar-link">About</router-link>
              </li>
              <li class="navbar-item">
                <router-link to="/contact" class="navbar-link">Contact</router-link>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    </div>
  </template>

<script>
    export default {
        data() {
            return {
            }
        },
        computed:{
        },
        methods:{
        },
        mounted(){
        }
    }
</script>

<style scoped>
.container {
  margin-bottom: 5px;
}

.header img {
  width: 1000px;
  height: 150px;
  padding-top: 1px;
}

.navbar-container {
  background-color: #f9f3df;
  width: 973px;
  height: 1%;
  margin-top: -3px;
}

.navbar-container ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin: 0;
  height: 1%;
  align-items: center;
  justify-content: center;
}

.navbar-item {
  margin: 0 35px;
}

.navbar-link {
  text-decoration: none;
  color: rgb(242, 5, 33);
  font-weight: 300;
  text-shadow: 0 1px 0 black;
  transition: all 0.3s ease;
}

.navbar-link:hover {
  font-weight: bold;
  background-color: rgb(127, 217, 214);
  color: #111111;
  text-shadow: none;
  padding: 14px 14px;
}
</style>