export default function toSentenceCaseWithDash(str) {
  return str.slice(0, 1) + str.slice(1).replace("_", " ").toLowerCase();
}
