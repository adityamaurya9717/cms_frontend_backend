import {createSlice} from '@reduxjs/toolkit'
const loginSlice = createSlice({
    name: 'loginSlice',
    initialState: {
      value: false,
    },
    reducers: {
      setLogin : (state)=>{
         state.value = !state.value
      }
    },
})