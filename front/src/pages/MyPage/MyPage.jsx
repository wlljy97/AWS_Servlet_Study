import React, { useEffect, useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';

function MyPage(props) {
    const [ profile, setProfile ] = useState ({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    // 페이지가 떴을 때 회원정보를 요청
    useEffect(() => {
        const getProfile = async() => {
            try{
                const response = await axios.get(`http://localhost:8080/servlet_study_jiwoo/mypage/profile`, {
                    headers: {
                        Authorization : localStorage.getItem("token")
                    }
                });
                setProfile(response.data);
            }catch(error){
                console.log(error);
            }

        }
        getProfile();
    }, []);

    return (
        <>
            <h1>마이페이지</h1>
            <p>username: {profile?.username}</p>
            <p>password: {profile?.password}</p>
            <p>name: {profile?.name}</p>
            <p>email: {profile?.email}</p>
        </>
    );
}

export default MyPage;