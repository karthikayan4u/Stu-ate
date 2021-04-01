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