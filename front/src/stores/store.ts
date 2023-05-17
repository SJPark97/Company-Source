import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import storage from "./storage";
import controlModalSlice from "./comparison/controlModal";
import leftSelectedCompanySlice from "./comparison/leftSelectedCompany";
import rightSelectedCompanySlice from "./comparison/rightSelectedCompany";
import controlInfoDetailSlice from "./info/controlInfoDetail";

const reducers = combineReducers({
    controlModal: controlModalSlice,
    leftSelectedCompany: leftSelectedCompanySlice,
    rightSelectedCompany: rightSelectedCompanySlice,
    controlInfoDetail: controlInfoDetailSlice,
    //   navbar: navbarSlice,
});

const persistConfig = {
    key: "root",
    storage,
};

const persistedReducer = persistReducer(persistConfig, reducers);

const store = configureStore({
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export default store;
