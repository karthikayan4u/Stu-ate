export interface User{
    email: string;
    password: string;
    username: string;
    mobileNumber: string;
    qualification: string;
    role: string;
    active: boolean;
    verify: boolean;
}
export interface Resource{
    resourceId: string;
    resourceName: string;
    resourceLink: string;
    resourcepdfLink: string;
    resourceCategory: string;
    createdOn: object;
    createdBy: User;
    imageUrl: string;
    active: boolean;
    verified: boolean;
}

export class ChatMessage{
    primary_user: User;
    secondary_user: User;
    sender: String;
    message: string;
    chat: Chat;

    constructor(primary_user: User,secondary_user: User, sender:String, message: string, chat: Chat){
        this.primary_user = primary_user;
        this.secondary_user = secondary_user;
        this.message = message;
        this.sender = sender;
        this.chat = chat;
    }
}

export interface Chat{
    chatId: String;
    primaryuser: User;
    secondaryUser: User;
    usersId: String;
    status: Boolean;
    lastSeen: Date;
}