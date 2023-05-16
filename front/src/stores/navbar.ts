import { createSlice } from "@reduxjs/toolkit";

interface navbar {
  location: string;
}

interface navbarAction {
  payload: navbar;
}

const initialState: navbar = {
  location: "/",
};

const navbarSlice = createSlice({
  name: "navbar",
  initialState,
  reducers: {
    setHome(state, action: navbarAction) {
      state.location = action.payload.location;
    },
  },
});

export const { setHome } = navbarSlice.actions;

export default navbarSlice.reducer;
