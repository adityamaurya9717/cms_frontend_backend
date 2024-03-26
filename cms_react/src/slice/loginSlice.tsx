import {createSlice} from '@reduxjs/toolkit'


const loginSlice = createSlice({
    name: 'loginSlice',
    initialState: {
      value: true,

    },
    reducers: {
      setLogin : (state)=>{
         state.value = !state.value
      }
    },
})