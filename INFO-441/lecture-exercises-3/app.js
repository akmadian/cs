import fetch from 'node-fetch'
import { parse } from 'node-html-parser'

const urls = [
  "https://www.npmjs.com/package/node-fetch",
  "https://wikileaks.org/10years/graphics.html"
]

const response = await fetch(urls[1])
const body = await response.text()

var root = parse(body)
const imgs = root.querySelectorAll("img")

console.log(`n of img tags: ${imgs.length}`)

// Check number of alt attributes by filtering, keeping only elements
// where the attributes contain the "alt=" pattern
console.log(`n of alt attributes: ${imgs.filter(img => img.rawAttrs.includes('alt=')).length}`)
