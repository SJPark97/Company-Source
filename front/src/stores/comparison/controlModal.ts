import { createSlice } from "@reduxjs/toolkit";

interface ImodalState {
  isLeftOpen: boolean,
  isRightOpen: boolean
}

const initialState: ImodalState = {
  isLeftOpen: false,
  isRightOpen: false
}

const controlModalSlice = createSlice({
  name: "selectCompany",
  initialState,
  reducers: {
    openLeftModal(state: ImodalState) {
      console.log("왼쪽 모달 열림")
      state.isLeftOpen = true
    },
    closeLeftModal(state: ImodalState) {
      console.log("왼쪽 모달 닫힘")
      state.isLeftOpen = false
    },
    openRightModal(state: ImodalState) {
      console.log("오른쪽 모달 열림")
      state.isRightOpen = true
    },
    closeRightModal(state: ImodalState) {
      console.log("오른쪽 모달 닫힘")
      state.isRightOpen = false
    },
  }
})

export const {
  openLeftModal,
  closeLeftModal,
  openRightModal,
  closeRightModal
} = controlModalSlice.actions;

export default controlModalSlice.reducer;