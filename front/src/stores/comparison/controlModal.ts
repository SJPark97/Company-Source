import { createSlice } from "@reduxjs/toolkit";

interface ImodalState {
  isOpen: boolean,
}

const initialState: ImodalState = {
  isOpen: false
}

const controlModalSlice = createSlice({
  name: "selectCompany",
  initialState,
  reducers: {
    openModal(state: ImodalState) {
      state.isOpen = true
    },
    closeModal(state: ImodalState) {
      state.isOpen = false
    },
  }
})

export const {
  openModal,
  closeModal
} = controlModalSlice.actions;

export default controlModalSlice.reducer;