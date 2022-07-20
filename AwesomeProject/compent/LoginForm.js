// import axios from 'axios';
// import { useContext, useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import { AuthenContext } from '../context/LoginContext';
// import React,  { Component } from 'react';
// import { Text, View } from 'react-native'
// // import styles from './LoginForm.module.css';

// export function LoginForm() {
//     let { login: loginCtx } = useContext(AuthenContext);
//     let navigate = useNavigate();

//     //ham hook
//     let [username, setUsername] = useState("");
//     let [password, setPassword] = useState("");

//     const login = async () => {
//         var urlencoded = new URLSearchParams();
//         urlencoded.append("username", username);
//         urlencoded.append("password", password);

//         var config = {
//             method: 'post',
//             url: 'http://54.150.115.104:8080/api/login',
//             headers: {
//                 'Content-Type': 'application/x-www-form-urlencoded'
//             },
//             data: urlencoded
//         };

//         try {
//             let response = await axios(config)

//             let result = response.data;
//             console.log(result)
//             loginCtx(result)
//             navigate("/", { replace: true });
//         } catch (error) {
//             console.log(error);
//         }
//     }

//     return (
// <View>
//     {/* <img src={logo} alt="logo"
//         className={imageLogo.circle}
//         style={imageLogo} /> */}
//     <Text>Login Form</Text>
//     <View>
//         <label>Username</label>
//         <input onChange={(e) => setUsername(e.target.value)} />
//     </View>
//     <View>
//         <label>Password</label>
//         <input onChange={(e) => setPassword(e.target.value)} />
//     </View>
//     <View>
//         <p>{username} - {password}</p>
//         <button type='button' onClick={login}>Login</button>
//     </View>
// </View>
//     )
// }
