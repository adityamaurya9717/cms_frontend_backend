import React from 'react'
import { useSelector,useDispatch } from 'react-redux'
import { UserLoginDto } from '../slice/UserDetailDtoSlice'
/**
 * 
 * @param param0 Protect Components used to protect routes
 * @returns 
 */
function ProtectedComponent({children}:any) {
   
  const userDetailService: UserLoginDto = useSelector((state:any)=>state.userDetailStore)
  const isLogin:boolean = userDetailService.isLoggedIn;


  return (
    <div>
      { isLogin ? children : "Not Logined"}
    </div>
  )
}

export default ProtectedComponent