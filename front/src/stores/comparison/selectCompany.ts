import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  companyInfo: Object,
}

const selectCompanySlice = createSlice({
  name: "selectCompany",
  initialState,
  reducers: {
    setselectCompany(state, action) {
      state.companyInfo = action.payload;
    }
  }
})

export const {
  setselectCompany,
} = selectCompanySlice.actions;

export default selectCompanySlice.reducer;