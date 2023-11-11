
import {createSlice} from '@reduxjs/toolkit'
 const toogleSlice = createSlice({
    name: 'toogleSlice',
    initialState: {
      value: true,
    },
    reducers: {
      toogle : (state)=>{
         state.value = !state.value
      }
    },
})

export default toogleSlice;