export function getDeleiveryOption(optionId){
    let getDeleiveryFee;
    deliveryOptions.forEach(option=>{
        if(option.dID === optionId){
            getDeleiveryFee = option;
        }
    });
    return getDeleiveryFee;
}


export const deliveryOptions = [
    {
        dID : '1',
        dDate : 7,
        dCharge : 0
    },
    {
        dID : '2',
        dDate : 4,
        dCharge : 149
    },
    {
        dID : '3',
        dDate : 1,
        dCharge : 249
    }
];