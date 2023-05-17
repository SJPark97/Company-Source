import { createSlice } from "@reduxjs/toolkit";

interface IInfoDetailState {
  isOpen: boolean
}

const initialState: IInfoDetailState = {
  isOpen: false
}

const controlInfoDetailSlice = createSlice({
  name: "infoDetail",
  initialState,
  reducers: {
    openInfoModal(state: IInfoDetailState) {
      state.isOpen = true
    },
    closeInfoModal(state: IInfoDetailState) {
      state.isOpen = false
    },
  }
})

export const {
  openInfoModal,
  closeInfoModal
} = controlInfoDetailSlice.actions;

export default controlInfoDetailSlice.reducer;