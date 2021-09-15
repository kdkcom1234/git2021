import { createSlice, PayloadAction } from "@reduxjs/toolkit";

export interface ContactItem {
  id: number;
  name: string;
  phone: string;
  email: string;
  memo?: string;
  createdTime: number;
}

interface ContactState {
  data: ContactItem[];
  isFetchted: boolean;
}

const initialState: ContactState = {
  data: [
    {
      id: 2,
      name: "홍길동",
      phone: "010-1234-5678",
      email: "hong@naver.com",
      memo: `홍길동 고객님입니다. 
      다음주에 찾아뵐 예정입니다.`,
      createdTime: new Date().getTime(),
    },
    {
      id: 1,
      name: "John Smith",
      phone: "010-4567-1234",
      email: "john@gmail.com",
      memo: `John Smith 고객님입니다. 
      다음주에 찾아뵐 예정입니다.`,
      createdTime: new Date().getTime(),
    },
  ],
  isFetchted: false,
};

const contactSlice = createSlice({
  name: "contact",
  initialState,
  reducers: {
    addContact: (state, action: PayloadAction<ContactItem>) => {
      const contact = action.payload;
      state.data.unshift(contact);
    },
    removeContact: (state, action: PayloadAction<number>) => {
      const id = action.payload;
      state.data.splice(
        state.data.findIndex((item) => item.id === id),
        1
      );
    },
    modifyContact: (state, action: PayloadAction<ContactItem>) => {
      const modifyItem = action.payload;
      const contactItem = state.data.find((item) => item.id === modifyItem.id);
      if (contactItem) {
        contactItem.name = modifyItem.name;
        contactItem.phone = modifyItem.phone;
        contactItem.email = modifyItem.email;
        contactItem.memo = modifyItem.memo;
      }
    },
  },
});

export const { addContact, removeContact, modifyContact } =
  contactSlice.actions;

export default contactSlice.reducer;
