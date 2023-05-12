import companyInfo from "@/models/companyInfo";
import { createSlice } from "@reduxjs/toolkit";

const initialState: companyInfo = {
  corpImg: "",
  corpName: "",
  corpSize: "",
  indutyName: "",
  isSelected: false,
}

const rightSelectedCompanySlice = createSlice({
  name: "rightSelectedCompany",
  initialState,
  reducers: {
    setRightCardCompany(state, action) {
      if (state.isSelected) {
        state = action.payload;
      }
    },
    selectRightCard(state) {
      state.isSelected = true
    },
    unselectRightCard(state) {
      state.isSelected = false
    }
  }
})

export const {
  setRightCardCompany,
  selectRightCard,
  unselectRightCard
} = rightSelectedCompanySlice.actions;

export default rightSelectedCompanySlice.reducer;