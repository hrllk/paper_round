<template>
  <v-app>
    <v-container fluid>
      <v-row no-gutters>
        <v-col cols="7" class="main-part d-none d-md-none d-lg-flex">
          <div class="d-flex">
            <v-img src="@/assets/logo2.svg" contain></v-img>
            <p>Paper Route</p>
          </div>
        </v-col>
        <v-col cols="12" lg="5" class="login-part d-flex align-center justify-center">
          <v-row no-gutters class="align-start">
            <v-col cols="12" class="login-part d-flex align-center justify-center flex-column">
              <div class="login-wrapper pt-md-4 pt-0">
                <v-tabs grow>
                  <v-tabs-slider></v-tabs-slider>
                  <v-tab :href="`#tab-login`">
                    LOGIN
                  </v-tab>
                  <v-tab :href="`#tab-newUser`">
                    New User
                  </v-tab>

                  <v-tab-item :value="'tab-login'" >
                    <v-form>
                      <v-container>
                        <v-row class="flex-column">
                          <v-col>
                            <p class="login-slogan display-2 text-center font-weight-medium my-10">HI, :D</p>
                            <v-btn height="45" block color="white" elevation="0" class="google text-capitalize">
                              <v-img src="@/assets/google.svg" max-width="30" class="mr-4"></v-img>
                              Sign in with Google</v-btn>
                          </v-col>
                          <v-col cols="12" class="d-flex align-center my-8">
                            <v-divider></v-divider>
                            <span class="px-5"> or </span>
                            <v-divider></v-divider>
                          </v-col>
                          <v-form>
                            <v-col>
                              <v-text-field
                                  v-model="email"
                                  :rules="emailRules"
                                  label="Email Address"
                                  required
                              ></v-text-field>
                              <v-text-field
                                  v-model="password"
                                  :rules="passRules"
                                  type="password"
                                  label="Password"
                                  hint="At least 6 characters"
                                  required
                              ></v-text-field>

                            </v-col>
                            <v-col class="d-flex justify-space-between">
                              <v-btn
                                  class="text-capitalize"
                                  large
                                  :disabled="password.length === 0 || email.length === 0"
                                  color="primary"
                                  @click="signin()"
                              >
                                Login</v-btn>
                              <v-btn large text class="text-capitalize primary--text">Forget Password</v-btn>
                            </v-col>
                          </v-form>
                        </v-row>
                      </v-container>
                    </v-form>
                  </v-tab-item>

                  <v-tab-item :value="'tab-newUser'" >
<!--                    <v-form v-on:submit.prevent="submitSignup">-->
                    <v-form>
                      <v-container>
                        <v-row class="flex-column">

                          <v-col>
                            <p class="login-slogan display-2 text-center font-weight-medium mt-10">Welcome</p>
                            <p class="login-slogan display-1 text-center font-weight-medium mb-10">Create your account</p>
                          </v-col>

                          <v-form>
                            <v-col>
                              <v-text-field
                                  v-model="signupForm.name"
                                  label="Full Name"
                                  required
                              ></v-text-field>
                              <v-text-field
                                  v-model="signupForm.email"
                                  :rules="emailRules"
                                  label="Email Address"
                                  required
                              ></v-text-field>
                              <v-text-field
                                  v-model="signupForm.password"
                                  :rules="passRules"
                                  type="password"
                                  label="Password"
                                  hint="At least 6 characters"
                                  required
                              ></v-text-field>
                            </v-col>
                            <v-col class="d-flex justify-space-between">
                              <v-btn
                                  large
                                  block
                                  :disabled="signupForm.name.length === 0 || signupForm.email.length === 0 || signupForm.password === 0"
                                  color="text-grey"
                                  @click="signup"
                              >
                                Create your account</v-btn>
                            </v-col>
                          </v-form>

                          <v-col cols="12" class="d-flex align-center my-4">
                            <v-divider></v-divider>
                            <span class="px-5"> or </span>
                            <v-divider></v-divider>
                          </v-col>

                          <v-btn height="45" block color="white" elevation="0" class="google text-capitalize">
                            <v-img src="@/assets/google.svg" max-width="30" class="mr-4"></v-img>
                            Sign in with Google</v-btn>
                        </v-row>
                      </v-container>
                    </v-form>
                  </v-tab-item>

                </v-tabs>
              </div>
            </v-col>
            <v-col cols="12" class="d-flex justify-center">
              <v-footer>
<!--                <div class="primary&#45;&#45;text">© 2014-2020 <a href="https://flatlogic.com/" class="text-decoration-none">Flatlogic</a>, LLC. All rights reserved.</div>-->
              </v-footer>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>

import axios from 'axios';
import VueCookies from "vue-cookies";
import router from "../../Routes";
// import store from "@/store/index";

  export default {
    name: 'Login',
    data() {
      return {
        email: '',
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /.+@.+/.test(v) || 'E-mail must be valid',
        ],
        // createFullName: '',
        // createEmail: '',
        // createPassword: '',
        password: '',
        passRules: [
          v => !!v || 'Password is required',
          v => v.length >= 6 || 'Min 6 characters'
        ],

        signupForm: {
          name: '',
          email: '',
          password: '',
        }

      }
    },
    methods: {
      submitSignup() {
        // axios.post(`/auth/signup`,this.signupForm, {
        // }).then(res => console.log("res: ", res));

        console.log("submitSIgnup!!!");
          console.log("signup params: ", this.signupForm);

      },
      signin() {

        //TODO: password 오류시 Alert 띄우기
        let frm = new FormData;
        frm.append('email', this.email);
        frm.append('password', this.password);
        // console.log("frm: ", frm);
        axios.post(`/auth/signIn`, frm, {
        }).then(function (res){
          // console.log('received accessToken after Login: ', res.data.accessToken);
          VueCookies.set('accessToken', res.data.accessToken);
          VueCookies.set('userId', res.data.userId);
          router.push("/");

        }).catch(function (err){
          console.log("err: ", err);
        });
      },
      signup() {
        let frm = new FormData;
        frm.append('name', this.signupForm.name);
        frm.append('email', this.signupForm.email);
        frm.append('password', this.signupForm.password);

        axios.post(`/auth/signup`,frm, {
        }).then(res => console.log("res: ", res));
      }
    }
  }


    // created() {
    //   if (window.localStorage.getItem('authenticated') === 'true') {
    //     console.log("hi login!!")
    //     this.$router.push('/dashboard');
    //   }
    // }


</script>

<style src="./Login.scss" lang="scss"/>
