import companyInfo from "@/models/companyInfo";
import { createSlice } from "@reduxjs/toolkit";

interface CompanyInfoPayloadAction {
  payload: companyInfo,
}

const initialState: companyInfo = {
  corpId: "",
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
    setRightCardCompany(state, action: CompanyInfoPayloadAction) {
      console.log("오른쪽 카드 수정")
      state.corpId = action.payload.corpId;
      state.corpImg = action.payload.corpImg;
      state.corpName = action.payload.corpName;
      state.corpSize = action.payload.corpSize;
      state.indutyName = action.payload.indutyName;
    },
    selectRightCard(state) {
      console.log("오른쪽 카드 select")
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