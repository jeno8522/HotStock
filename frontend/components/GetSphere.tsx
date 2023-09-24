import { fetchKeywords } from "@/utils";
import TextSphere from "./Sphere";
import { KeywordProps } from "@/types";

const GetSphere = async () => {
  const allKeywords = await fetchKeywords();

  const sublist: string[] = [];
  for (let i = 0; i < 50; i++) {
    if (allKeywords[i]) {
      sublist.push(allKeywords[i].text);
    }
  }

  return (
    <div>
      <TextSphere data={sublist} fullData={allKeywords} />
    </div>
  );
};

export default GetSphere;
