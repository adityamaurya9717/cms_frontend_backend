import { configureStore } from '@reduxjs/toolkit'
import toogleSlice from './slice/toggleSlice'

// config Store Contain a reducer 
export default configureStore({
  reducer: {
    toogle:toogleSlice.reducer
  },
  
})