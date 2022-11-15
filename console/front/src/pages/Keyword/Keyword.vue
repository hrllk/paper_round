
<template>
  <v-container fluid>
    <div class="dashboard-page">
      <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
        <h1 class="page-title">Keywords</h1>
<!--        <h1 class="page-title">키워드</1>-->
      </v-row>

      <v-row>
        <v-col cols="6" offset="3">

          <wordCloud
              :data="defaultWords"
              nameKey="keyword"
              valueKey="useCnt"
              :color="myColors"
              :showTooltip="true">
<!--              :wordClick="wordClickHandler">-->
          </wordCloud>        </v-col>
      </v-row>
      <v-row>
        <v-col cols="6" offset="3">
          <v-text-field
              v-model="keyword"
          >
            <v-btn slot="append" icon
                   @click="append()">
              <v-icon style="font-size: 28px">mdi-bookmark-plus-outline</v-icon>
            </v-btn>
            <v-btn slot="append" icon
                   @click="save()">
              <v-icon style="font-size: 28px">mdi-content-save-check</v-icon>
            </v-btn>


          </v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="8" offset="2">
          <v-btn v-for="(keyword, index) in keywordList" style="margin: 5px" :key="index" @click="remove(index, keyword)"> <!-- success case -->
            {{ keyword }}
          </v-btn>

        </v-col>
      </v-row>
    </div>

    <ConfirmDialog ref="removeDialog"/>

  </v-container>
</template>
<script>
import axios from "axios";
import store from "@/store/index";
import VueCookies from "vue-cookies";
import ConfirmDialog from "@/components/Dialog/ConfirmDialog";
import wordCloud from "vue-wordcloud";

export default {

  components: {
    ConfirmDialog,
    wordCloud
  },
  data () {
    return {
      keyword: null,
      // keywordList: [],
      keywordList: new Array(),
      // keywordRule: [
        // v => !!v || 'E-mail is required',
        // v => /.+@.+/.test(v) || 'E-mail must be valid',
      // ],

      myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
      defaultWords: [],
    }
  },
  // render first at load dom
  async created() {

    const config = {
      url: `/api/v1/keywords`,
      method: 'GET'
    }
    let response = await axios(config);
    this.defaultWords = response.data;
    console.log("data : ",response.data);

    console.log("userId: ", VueCookies.get('userId'))
    axios.get(`/api/v1/users/${VueCookies.get('userId')}/keywords`, {})
        .then(res => this.keywordList = res.data)
        .catch(function (err) {
          console.log('err.response:', err.response);
          if (err.response.status === 401) {
            alert('token is expired')
            store.commit('removeToken')
          }
    });

  },

  methods: {
    acceptRemove(keyword){

      console.log('삭제요청받은 키워드: ', keyword);
      axios.delete(`/api/v1/keywords`, {
        data: {
          userId: VueCookies.get('userId'),
          keyword: keyword
        }
      }).then(res =>
          console.log('res: ', res),
          // this.$router.go()
          this.$router.go()
      );


    },
    remove(idx, keyword){

      this.$refs.removeDialog.showDialog(keyword);

    },
    async append(){

      if (this.keywordList.includes(this.keyword)){
        alert("already exist");
        this.keyword = '';
        return;
      }

      this.keywordList.push(this.keyword)
      console.log("not registered");

      let vue = this;
      axios.post(`/api/v1/users/keywords`, {

        userId: VueCookies.get('userId'),
        keyword: this.keyword
      }, {}).then(function (res) {

        // this.keyword = '';
        vue.keyword = '';
        console.log('res: ', res);
      })


    },
    async save() {
      //키워드 등록
      const config = {
        url: `/api/v1/users/${VueCookies.get('userId')}/keywords`,
        method: "POST",
      };

      const response = await axios(config);
      console.log('res: ', response);
      console.log("save");

    }

  }
}
</script>
<!--<style src="./Index.scss" lang="scss"/>-->
