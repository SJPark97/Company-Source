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
      state.isLeftOpen = true
    },
    closeLeftModal(state: ImodalState) {
      state.isLeftOpen = false
    },
    openRightModal(state: ImodalState) {
      state.isRightOpen = true
    },
    closeRightModal(state: ImodalState) {
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