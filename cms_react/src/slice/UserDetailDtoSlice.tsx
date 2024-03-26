import { createSlice } from '@reduxjs/toolkit'



export type UserLoginDto = {
    isLoggedIn: boolean,
    email: string | null,
    roles: string[] | null,

}
const userDetailInit: UserLoginDto = {
    isLoggedIn: true,
    email: null,
    roles: null
}
const userDetailSlice = createSlice({
    name: "user_detail_slice",
    initialState: userDetailInit,
    reducers: {
        setUserDetail: (state: UserLoginDto, payload: any):UserLoginDto => {
            return payload
        },
        removeUserDetail: (state: UserLoginDto):UserLoginDto => {
            return {
                isLoggedIn: false,
                email: null,
                roles: null
            } as UserLoginDto
        }
    }
})

export const { setUserDetail, removeUserDetail } = userDetailSlice.actions;

export default userDetailSlice.reducer;