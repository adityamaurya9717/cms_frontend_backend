import { configureStore } from '@reduxjs/toolkit'
import toogleSlice from './slice/toggleSlice'
import userDetailSlice from './slice/UserDetailDtoSlice'

// config Store Contain a reducer 
export default configureStore({
  reducer: {
    toogle:toogleSlice,
    userDetailStore:userDetailSlice
  },
  
})