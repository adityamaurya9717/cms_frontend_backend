
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
export const {toogle} = toogleSlice.actions;
export default toogleSlice.reducer;