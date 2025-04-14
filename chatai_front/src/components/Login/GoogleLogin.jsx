import React, { useState } from 'react'

import { GoogleAuthProvider, signInWithPopup, signOut } from 'firebase/auth';
import { auth } from '../../util/firebase.ts';

const GoogleLogin = () => {
  const [user, setUser] = useState(null);

  const handleGoogleLogin = async () => {
    const provider = new GoogleAuthProvider();
    try {
      const result = await signInWithPopup(auth ,provider);
      const user = result.user;
      setUser(user);
      console.log(user)
    } catch (error) {
      console.error(error.message);
    }
  }

  const handleGoogleLogOut = async () => {
    try {
      await signOut(auth);
      setUser(null);
      console.log('로그아웃 성공')
    } catch (error) {
      console.log('로그아웃 실패', error.message);
    }
  }

  return (
    <div>
      {
        !user ? (
          <div>
            <h1>로그인</h1>
            <button onClick={handleGoogleLogin}>Google로 로그인</button>
          </div>
        ) : (
          <div>
            <h1>환영합니다, {user.displayName}님</h1>
            <button onClick={handleGoogleLogOut}>로그아웃</button>
          </div>
        )
      }
    </div>
  )
}

export default GoogleLogin