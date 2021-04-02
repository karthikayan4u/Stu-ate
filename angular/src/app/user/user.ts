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
    message: string;

    constructor(primary_user: User,secondary_user: User, message: string){
        this.primary_user = primary_user;
        this.secondary_user = secondary_user;
        this.message = message;
    }
}