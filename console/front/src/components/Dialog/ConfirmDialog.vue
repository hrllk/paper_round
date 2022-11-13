<template>
  <div class="text-center">
    <v-dialog
        v-model="dialog"
        width="500"
    >

      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          Privacy Policy
        </v-card-title>

        <v-card-text>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
          magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
          consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
          pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
          est laborum.
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="primary"
              text
              @click="answer()"
          >
            I accept
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<!--<script>-->
<!--export default {-->
<!--  name: "ConfirmDialog"-->
<!--}-->
<!--</script>-->
<script>
export default {
  name: "ConfirmDialog",

  data () {
    return {
      dialog: false,
      keyword: ''
    }
  },
  methods: {
    showDialog(keyword){
      // console.log('넘어온키워드: ', keyword)
      this.keyword = keyword;
      this.dialog = true;

    },
    answer() {

      // console.log('answer!!');
      // dialog false

      // this.$emit('acceptRemove');
      this.$parent.acceptRemove(this.keyword);
      this.dialog = false;
    }
  }

}
</script>

<style scoped>

</style>