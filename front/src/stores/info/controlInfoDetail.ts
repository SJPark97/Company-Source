import { createSlice } from "@reduxjs/toolkit";

interface IInfoDetailState {
    modalIdx: string,
    isOpen: boolean
}

interface infoModalAction {
    payload: IInfoDetailState
}

const initialState: IInfoDetailState[] = [
    { modalIdx: '101', isOpen: false },
    { modalIdx: '102', isOpen: false },
    { modalIdx: '103', isOpen: false },
    { modalIdx: '104', isOpen: false },
    { modalIdx: '106', isOpen: false },
    { modalIdx: '105', isOpen: false },
    { modalIdx: '108', isOpen: false },
    { modalIdx: '109', isOpen: false },
    { modalIdx: '110', isOpen: false },
    { modalIdx: '111', isOpen: false },
    { modalIdx: '113', isOpen: false },
    { modalIdx: '303', isOpen: false },
    { modalIdx: '405', isOpen: false },
    { modalIdx: '408', isOpen: false }
]

const controlInfoDetailSlice = createSlice({
    name: "infoDetail",
    initialState,
    reducers: {
        openInfoModal(state: IInfoDetailState[], action) {
            state.filter((oldState) => {
                return ((oldState.modalIdx === action.payload.modalIdx) ? action.payload : oldState)
            })
            return state
        },
        closeInfoModal(state: IInfoDetailState[], action) {
            state.filter((oldState) => {
                return ((oldState.modalIdx === action.payload.modalIdx) ? action.payload : oldState)
            })
            return state
        },
    }
})

export const {
    openInfoModal,
    closeInfoModal
} = controlInfoDetailSlice.actions;

export default controlInfoDetailSlice.reducer;