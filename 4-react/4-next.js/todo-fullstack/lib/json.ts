export function toSerializable<Type>(data: Type) {
  if (data !== undefined) {
    let intCount = 0,
      repCount = 0;
    const json = JSON.stringify(data, (_, v) => {
      if (typeof v === "bigint") {
        intCount++;
        return `${v}#bigint`;
      }
      return v;
    });
    const res = json.replace(/"(-?\d+)#bigint"/g, (_, a) => {
      repCount++;
      return a;
    });
    if (repCount > intCount) {
      // You have a string somewhere that looks like "123#bigint";
      throw new Error(`BigInt serialization conflict with a string value.`);
    }
    return JSON.parse(res) as Type;
  }
}
