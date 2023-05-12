import companyInfo from "@/models/companyInfo";
import { createSlice } from "@reduxjs/toolkit";

interface CompanyInfoPayloadAction {
  payload: companyInfo,
}

interface openCardPayloadAction {
  payload: boolean
}

const initialState: companyInfo = {
  corpImg: "",
  corpName: "",
  corpSize: "",
  indutyName: "",
  isSelected: false,
}

const leftSelectedCompanySlice = createSlice({
  name: "leftSelectedCompany",
  initialState,
  reducers: {
    setleftCardCompany(state, action) {
      if (state.isSelected) {
        state = action.payload;
      }
    },
    selectLeftCard(state) {
      state.isSelected = true
    },
    unselectLeftCard(state) {
      state.isSelected = false
    }
  }
})

export const {
  setleftCardCompany,
  selectLeftCard,
  unselectLeftCard
} = leftSelectedCompanySlice.actions;

export default leftSelectedCompanySlice.reducer;