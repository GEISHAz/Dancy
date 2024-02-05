import { HttpStatusCode } from "axios";
import { useMemberStore } from "@/stores/member.js"
import { useRouter } from "vue-router";

const router = useRouter();
const memberStore = useMemberStore()
const { userLogout } = memberStore

function errorProcess(e) {
    console.log(e)
    let statusCode = e.response.status

    if (statusCode === HttpStatusCode.Unauthorized) {
      alert("로그인을 다시 진행해야 합니다.")
      userLogout()
      router.push("/")
    } else if (statusCode === HttpStatusCode.BadRequest) {
      const firstErrorMessage = e.response.data[0].message
      alert(firstErrorMessage)
    }
}
  
export { errorProcess };