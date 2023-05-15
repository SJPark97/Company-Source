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
    setLeftCardCompany(state, action: CompanyInfoPayloadAction) {
      console.log("왼쪽 카드 수정")
      state.corpImg = action.payload.corpImg;
      state.corpName = action.payload.corpName;
      state.corpSize = action.payload.corpSize;
      state.indutyName = action.payload.indutyName;
    },
    selectLeftCard(state) {
      console.log("왼쪽 카드 select")
      state.isSelected = true
    },
    unselectLeftCard(state) {
      state.isSelected = false
    }
  }
})

export const {
  setLeftCardCompany,
  selectLeftCard,
  unselectLeftCard
} = leftSelectedCompanySlice.actions;

export default leftSelectedCompanySlice.reducer;